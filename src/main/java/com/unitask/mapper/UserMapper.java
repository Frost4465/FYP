package com.unitask.mapper;

import com.unitask.dto.DropdownResponse;
import com.unitask.dto.user.ProfileResponse;
import com.unitask.entity.AppUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class UserMapper {

    public DropdownResponse toDropdown(AppUser appUser) {
        if (Objects.isNull(appUser)) {
            return null;
        }

        List<DropdownResponse> dropdownResponseList = new ArrayList<>();
        DropdownResponse dropdownResponse = new DropdownResponse();
        dropdownResponse.setId(appUser.getId());
        dropdownResponse.setLabel(appUser.getLastName() + " " + appUser.getFirstName());
        dropdownResponseList.add(dropdownResponse);
        return dropdownResponse;
    }

    public List<DropdownResponse> toDropdown(Collection<AppUser> appUserList) {
        if (CollectionUtils.isEmpty(appUserList)) {
            return null;
        }

        List<DropdownResponse> dropdownResponseList = new ArrayList<>();
        for (AppUser appUser : appUserList) {
            dropdownResponseList.add(toDropdown(appUser));
        }

        return dropdownResponseList;
    }

    public ProfileResponse toResponse(AppUser appUser) {
        if (Objects.isNull(appUser)) {
            return null;
        }

        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setName(appUser.getLastName() + " " + appUser.getFirstName());
        profileResponse.setId(appUser.getId());
        return profileResponse;
    }
}
