package com.example.stewa.fitnesscrazy01;

public final class ContactContact {

    private ContactContact() {
    }

    public static class ContactEntry {
        //coulnm names for table UserInformation, Logs
        public static final String TABLE_NAME = "USER_INFORMATION";
        public static final String USER_NAME = "user_name";
        public static final String FIRST_NAME = "First_Name";
        public static final String LAST_NAME = "Last_Name";
        public static final String PASSWORD = "password";
        public static final String AGE = "age";
        public static final String WEIGHT = "weight";
        public static final String HEIGHT = "height";
        //Colunm names fpr table Goals and steps
        public static final String GoalWeight = "goalweight";
        public static final String GoalSteps = "goalsteps";
        public static final String AmountOfSteps = "AmountOfSteps";
        public static final String StepsDate = "Date";

        //variable declarations
        public static int NumberOfEntries;
        public static String[] DateArray;
        public static String[] WeightArray;
        public static String[] HeightArray;
        public static String USER_NAMEs;
        public static String FIRST_NAMEs;
        public static String LAST_NAMEs;
        public static String GoalWeights;
        public static String GoalStep;
        public static String WEIGHTs;
        public static int UserWeight;
        public static String HEIGHTs;
        public static Boolean show;
        public static Boolean showSteps;
        public static String PASSWORDs;
        public static String AGEs;

        //getter and setters
        public static String[] getDateArray() {
            return DateArray;
        }
        public static void setDateArray(String[] dateArray) {
            DateArray = dateArray;
        }
        public static String[] getWeightArray() {
            return WeightArray;
        }
        public static void setWeightArray(String[] weightArray) {
            WeightArray = weightArray;
        }
        public static String getGoalWeights() {
            return GoalWeights;
        }
        public static void setGoalWeights(String goalWeights) {
            GoalWeights = goalWeights;
        }
        public static String getGoalStep() {
            return GoalStep;
        }
        public static void setGoalStep(String goalStep) {
            GoalStep = goalStep;
        }
        public static String getUSER_NAMEs() {
            return USER_NAMEs;
        }
        public static void setUSER_NAMEs(String USER_NAMEs) {
            ContactEntry.USER_NAMEs = USER_NAMEs;
        }
        public static String getFIRST_NAMEs() {
            return FIRST_NAMEs;
        }
        public static void setFIRST_NAMEs(String FIRST_NAMEs) {
            ContactEntry.FIRST_NAMEs = FIRST_NAMEs;
        }
        public static String getLAST_NAMEs() {
            return LAST_NAMEs;
        }

        public static void setLAST_NAMEs(String LAST_NAMEs) {
            ContactEntry.LAST_NAMEs = LAST_NAMEs;
        }
        public static String getPASSWORDs() {
            return PASSWORDs;
        }
        public static void setPASSWORDs(String PASSWORDs) {
            ContactEntry.PASSWORDs = PASSWORDs;
        }
        public static String getAGEs() {
            return AGEs;
        }
        public static void setAGEs(String AGEs) {
            ContactEntry.AGEs = AGEs;
        }
        public static String getWEIGHTs() {
            return WEIGHTs;
        }
        public static void setWEIGHTs(String WEIGHTs) {
            ContactEntry.WEIGHTs = WEIGHTs;
        }
        public static String getHEIGHTs() {
            return HEIGHTs;
        }
        public static void setHEIGHTs(String HEIGHTs) {
            ContactEntry.HEIGHTs = HEIGHTs;
        }
        public static int getUserWeight() {
            return UserWeight;
        }
        public static void setUserWeight(int userWeight) {
            UserWeight = userWeight;
        }
        public Boolean getShow() {
            return show;
        }
        public void setShow(Boolean show) {
            this.show = show;
        }
        public static String[] getHeightArray() {
            return HeightArray;
        }
        public static void setHeightArray(String[] heightArray) {
            HeightArray = heightArray;
        }
    }
}
