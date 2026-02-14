package com.cement.app.identity.dto;

import lombok.Data;

@Data
public class AssignBranchRequest {

    private Long branchId;
    private Long roleId;
}
