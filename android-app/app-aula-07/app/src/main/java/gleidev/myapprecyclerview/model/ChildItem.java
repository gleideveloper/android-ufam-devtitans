package gleidev.myapprecyclerview.model;

public class ChildItem {
    private String childItemTitle;
    private int childItemRate;

    public ChildItem(String childItemTitle, int childItemRate) {
        this.childItemTitle = childItemTitle;
        this.childItemRate = childItemRate;
    }

    public String getChildItemTitle() {
        return childItemTitle;
    }

    public void setChildItemTitle(String childItemTitle) {
        this.childItemTitle = childItemTitle;
    }

    public int getChildItemRate() {
        return childItemRate;
    }

    public void setChildItemRate(int childItemRate) {
        this.childItemRate = childItemRate;
    }
}
