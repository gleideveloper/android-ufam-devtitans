package gleidev.myapprecyclerview.model;

import java.util.List;

public class ParentItem {
    private String parentItemTitle;
    private List<ChildItem> childItemList;

    public ParentItem(String parentItemTitle, List<ChildItem> childItemList) {
        this.parentItemTitle = parentItemTitle;
        this.childItemList = childItemList;
    }

    public String getParentItemTitle() {
        return parentItemTitle;
    }

    public void setParentItemTitle(String parentItemTitle) {
        this.parentItemTitle = parentItemTitle;
    }

    public List<ChildItem> getChildItemList() {
        return childItemList;
    }

    public void setChildItemList(List<ChildItem> childItemList) {
        this.childItemList = childItemList;
    }
}
