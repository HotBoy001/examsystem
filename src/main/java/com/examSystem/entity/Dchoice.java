package com.examSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dchoice {
    private Integer dcId;

    private String dcContent;

    private String dcA;

    private String dcB;

    private String dcC;

    private String dcD;

    private String dcCorrect;

   }