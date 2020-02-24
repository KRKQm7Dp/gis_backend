package com.gis_server.utils;

import com.gis_server.dto.PermissionDto;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    public static void setPermissionsTree(Integer parentId, List<PermissionDto> permissionsAll, List<PermissionDto> permissionDtoList) {
        for (PermissionDto per : permissionsAll) {
            if (per.getParentId().equals(parentId)) {
                permissionDtoList.add(per);
                if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
                    List<PermissionDto> child = new ArrayList<>();
                    per.setChildren(child);
                    setPermissionsTree(per.getId(), permissionsAll, child);
                }
            }
        }
    }

}
