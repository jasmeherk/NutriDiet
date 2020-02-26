package model;

public class StoredData {
    CalorieCounter calData;
    Goals goalData;
    Attributes attrData;

    public StoredData(CalorieCounter calData, Goals goalData, Attributes attrData) {
        this.calData = calData;
        this.goalData = goalData;
        this.attrData = attrData;
    }

}
