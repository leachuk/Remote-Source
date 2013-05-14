package au.com.entitysolutions.taskflows.rss.model;

import java.net.URL;
import java.util.Iterator;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.io.XmlReader;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;


public class FeedReader {
    Collection<Feed> myFeedEntries = new ArrayList<Feed>();
      
    public FeedReader() {
    }

    /**
     * @param arg
     * @return
     */
    public Collection<Feed> processFeed(String arg){
        if (arg != null) {
            try {
                URL feedUrl = new URL(arg);
                final SyndFeedInput input = new SyndFeedInput();
                final SyndFeed feed = input.build(new XmlReader(feedUrl));
                for (final Iterator entryIter = feed.getEntries().iterator();
                     entryIter.hasNext();)             
                    {
                        SyndEntry entry = (SyndEntry) entryIter.next();
                        addEntry(feed.getTitle(),feed.getDescription(),entry);
                    }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        return myFeedEntries;
    }
    
    public void addEntry(String titleHead, String titleDesc, SyndEntry entry) {
        //Prep variables used in loops
        String title = entry.getTitle();
        String link = entry.getLink();
        SyndContent contentDesc = (SyndContent) entry.getDescription();
        String description = contentDesc.getValue();        
        if (title.length() < 60 ) {
            description = StringUtils.substring(description, 0, 80);    
        } else {
            description = StringUtils.substring(description, 0, 60);    
        }
        int len = StringUtils.lastIndexOf(description, " ");
        description = StringUtils.substring(description,0,len + 1);
        description = description + " [...]";        
        title = StringEscapeUtils.unescapeXml(title);
        description = StringEscapeUtils.unescapeXml(description);
        myFeedEntries.add(new Feed(titleHead,titleDesc,title,link,description));
    }
}
