/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package it.codeland.academy.core.models;

import java.util.*;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class)
public class FootelModel {
    private static final Logger LOG = LoggerFactory.getLogger(FootelModel.class);

    @SlingObject
    private Resource currentResource;

    public List<Map<String, String>> getLinks() {
        List<Map<String, String>> BottomNavLinks = new ArrayList<>();
        try {
            Resource DialogLinks = currentResource.getChild("footerNavigations");
            if (DialogLinks == null) {
                return Collections.emptyList();
            }
            if (DialogLinks != null) {
                for (Resource item : DialogLinks.getChildren()) {
                    Map<String, String> menuMap = new HashMap<String, String>();
                    menuMap.put("title", item.getValueMap().get("title", String.class));
                    menuMap.put("url", item.getValueMap().get("url", String.class));
                    menuMap.put("type", item.getValueMap().get("type", String.class));
                    BottomNavLinks.add(menuMap);
                }
            }
        } catch (Exception e) {
            LOG.error("\n ERROR Getting while fetcching data", e.getMessage());
        }
        return BottomNavLinks;
    }
    
    public List<Map<String, String>> getSubLinks() { 
        List<Map<String, String>> BottomSubNavLinks = new ArrayList<>();
        try {
            Resource DialogLinks = currentResource.getChild("footerSubNavigations");
            if (DialogLinks == null) {
                return Collections.emptyList();
            }
            if (DialogLinks != null) {
                for (Resource item : DialogLinks.getChildren()) {
                    Map<String, String> menuMap = new HashMap<String, String>();
                    menuMap.put("title", item.getValueMap().get("title", String.class));
                    menuMap.put("url", item.getValueMap().get("url", String.class));
                    menuMap.put("type", item.getValueMap().get("type", String.class));
                    BottomSubNavLinks.add(menuMap);
                }
            }
        } catch (Exception e) {
            LOG.error("\n ERROR Getting while fetcching data", e.getMessage());
        }
        return BottomSubNavLinks;
    }
}