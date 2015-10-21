package Presentation.Bean;

import BusinessLogic.Controller.ManageUser;
import BusinessLogic.Controller.Util;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="loginUserBean")
@ViewScoped
public class loginUserBean
{
    private String email;
    private String password;
    private String message;
    private String hostName=getHost();
    private long counter = getC();
    private String sessionId=getSession();

    public loginUserBean()
    {
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void loginUser()
        throws SQLException
    {
        ManageUser manageUser = new ManageUser();
        if(manageUser.login(getEmail(), getPassword()))
            manageUser.renderIndex();
        else
            setMessage("Correo y/o contrase\361a incorrectos, intente nuevamente");
    }

    public void logoutUser()
        throws SQLException
    {
        ManageUser manageUser = new ManageUser();
        manageUser.logout();
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getHostName()
    {
        return hostName;
    }

    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }

    private String getHost()
    {
        String h = null;
        try
        {
            h = InetAddress.getLocalHost().getHostName();
        }
        catch(UnknownHostException e) { }
        return h;
    }

    public long getCounter()
    {
        return counter;
    }

    public void setCounter(long counter)
    {
        this.counter = counter;
    }

    private long getC()
    {
        HttpSession session = Util.getSession();
        if(session.getAttribute("counter") == null)
        {
            counter = 1;
        } else
        {
            counter = (long)session.getAttribute("counter");
            counter++;
        }
        session.setAttribute("counter", counter);
        return counter;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    private String getSession()
    {
        HttpSession session = Util.getSession();
        sessionId = session.getId();
        return sessionId;
    }

    
}