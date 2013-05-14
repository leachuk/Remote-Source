package au.com.entitysolutions.taskflows.common.view;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import oracle.adf.share.ADFContext;
import oracle.jbo.domain.Number;

public class UserBean {

    /* Obtained from LDAP Display Name */
    private String fullName;
    private String skin;
    private String userName;
    private String displayName;
    private boolean active;
    private Number orgId;
    private Number personId;
    private String OrganisationName;

    public UserBean() {
        super();
    }

    public String getFullName() {
        if (fullName == null) {
            fullName =
                    ADFContext.getCurrent().getSecurityContext().getUserName();
            // get the profile object for current user
            /*
            try
            {
                WCUserProfile userProfile
                    = ProfileFactory.getProfileManager().getProfile(userName);
                fullName = userProfile.getDisplayName();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            */
        }
        return fullName;
    }
    
    public Number getOrgId(){
        return orgId;
    }
    
    public void setOrgId(Number orgId){
        this.orgId = orgId;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getSkin() {
        return skin;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /** Get the user's IP Address */
    public String getIpAddress() {
        ExternalContext context =
            FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest)context.getRequest();
        return req.getRemoteAddr();
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setPersonId(Number personId) {
        this.personId = personId;
    }

    public Number getPersonId() {
        return personId;
    }

    public void setOrganisationName(String OrganisationName) {
        this.OrganisationName = OrganisationName;
    }

    public String getOrganisationName() {
        return OrganisationName;
    }
}

