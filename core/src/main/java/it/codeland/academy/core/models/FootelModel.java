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

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, adapters = FootelModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FootelModel {
    private static final Logger LOG = LoggerFactory.getLogger(FootelModel.class);

    @SlingObject
    private Resource currentResource;

    @ValueMapValue
    String copyrightMsg;

    @ValueMapValue
    String copyrightImage;

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
    
    public List<Map<String, String>> getSocialMedias() {
        List<Map<String, String>> socialsMap = new ArrayList<>();
        try {
            Resource navsDialog = currentResource.getChild("socials");

            if (navsDialog == null) {
                return Collections.emptyList();
            }

            if (navsDialog != null) {
                for (Resource navItem : navsDialog.getChildren()) {
                    Map<String, String> mapNavItem = new HashMap<>();
                    mapNavItem.put("link", navItem.getValueMap().get("link", String.class));
                    mapNavItem.put("icon", navItem.getValueMap().get("icon", String.class));
                    mapNavItem.put("target", navItem.getValueMap().get("target", String.class));
                    socialsMap.add(mapNavItem);
                }
            }
        } catch (Exception e) {
            LOG.info("ERROR while getting socials ", e.getMessage());
        }
        return socialsMap;
    }
    
    public List<Map<String, String>> getUtilsItems() {
        List<Map<String, String>> utilNavLinksMap = new ArrayList<>();
        try {
            Resource navsDialog = currentResource.getChild("utilNavItems");
            if (navsDialog != null) {
                for (Resource navItem : navsDialog.getChildren()) {
                    Map<String, String> mapNavItem = new HashMap<>();
                    mapNavItem.put("link", navItem.getValueMap().get("link", String.class));
                    mapNavItem.put("linkTo", navItem.getValueMap().get("linkTo", String.class));
                    mapNavItem.put("target", navItem.getValueMap().get("target", String.class));
                    utilNavLinksMap.add(mapNavItem);
                }
            }
        } catch (Exception e) {
            LOG.info("ERROR while getting first row links ", e.getMessage());
        }
        return utilNavLinksMap;
    }
    

    public String getCopyrightMsg() {
        return copyrightMsg;
    }

    public String getCopyrightImage() {
        return copyrightImage;
    }
}