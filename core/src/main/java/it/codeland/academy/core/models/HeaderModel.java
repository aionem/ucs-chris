package it.codeland.academy.core.models;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Christain
 */
@Model(adaptables = Resource.class)
public class HeaderModel {

    private static final Logger LOG = LoggerFactory.getLogger(HeaderModel.class);


    @SlingObject
    Resource componentResource;

    public Page getTopPage() {
        PageManager pageManager = componentResource.getResourceResolver().adaptTo(PageManager.class);
        LOG.debug("=============><>< DEBUGING" + pageManager);
        Page currentPage = pageManager.getContainingPage(componentResource);
       
        return currentPage.getAbsoluteParent(3);
    }

    public List<Map<String, String>> getTopNavLinks() {
        List<Map<String, String>> TopNavLinks = new ArrayList<>();
        try {
            Resource DialogLinks = componentResource.getChild("headerTopNavigations");
            if (DialogLinks == null) {
                return Collections.emptyList();
            }
            if (DialogLinks != null) {
                for (Resource item : DialogLinks.getChildren()) {
                    Map<String, String> menuMap = new HashMap<String, String>();
                    menuMap.put("title", item.getValueMap().get("title", String.class));
                    menuMap.put("link", item.getValueMap().get("link", String.class));
                    menuMap.put("icon", item.getValueMap().get("icon", String.class));
                    TopNavLinks.add(menuMap);
                }
            }
        } catch (Exception e) {
            LOG.error("\n ERROR Getting while fetcching data", e.getMessage());
        }
        return TopNavLinks;
    }

}
