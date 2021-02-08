package cn.rxy.trial.rxywebsitedemo.util;

public class MContainer {
    
    private String time;
    private boolean disabled;

    public MContainer(String time, boolean disabled) {
        this.time = time;
        this.disabled = disabled;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    

}