package com.example.javamobileapplication;

public class Approval {
    private String firstName;
    private String lastName;
    private String province;
    private String district;
    private String region;
    private String season;
    private String submitDate;
    private float harvestAmount;
    private float cultivatedAmount;
    private int status;
    private String reason;

    public Approval() {
    }

    public Approval(String firstName, String lastName, String province, String district, String region, String season, String submitDate, float harvestAmount, float cultivatedAmount, int status, String reason) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.province = province;
        this.district = district;
        this.region = region;
        this.season = season;
        this.submitDate = submitDate;
        this.harvestAmount = harvestAmount;
        this.cultivatedAmount = cultivatedAmount;
        this.status = status;
        this.reason = reason;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public float getHarvestAmount() {
        return harvestAmount;
    }

    public void setHarvestAmount(float harvestAmount) {
        this.harvestAmount = harvestAmount;
    }

    public float getCultivatedAmount() {
        return cultivatedAmount;
    }

    public void setCultivatedAmount(float cultivatedAmount) {
        this.cultivatedAmount = cultivatedAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
