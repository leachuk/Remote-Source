package au.com.entitysolutions.taskflows.rss.model;

public class Feed {
    public Feed() {
    }
    
    String titleHead; 
    String titleDesc;
    String title;
    String link;
    String description;
    
    public Feed(String titleHead, String titleDesc, String title, String link, String description) {
        super();
        this.titleHead = titleHead;
        this.titleDesc = titleDesc;
        this.title = title;
        this.link = link;
        this.description = description;
    }
    
    public void setTitleHead(String titleHead) {
        this.titleHead = titleHead;
    }
    
    public String getTitleHead() {
        return titleHead;
    }
    
    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }
    
    public String getTitleDesc() {
        return titleDesc;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    public String getLink() {
        return link;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}
