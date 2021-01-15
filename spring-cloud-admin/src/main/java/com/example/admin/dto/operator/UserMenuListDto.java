package com.example.admin.dto.operator;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class UserMenuListDto {

    private Long id;

    private Long parentId;

    private Integer level;

    private String name;

    private String uri;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<UserMenuListDto> childMenuList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<UserMenuListDto> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<UserMenuListDto> childMenuList) {
        this.childMenuList = childMenuList;
    }
}
