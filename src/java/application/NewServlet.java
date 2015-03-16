package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.*;

public class NewServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(NewServlet.class);
    
    public EntityMy entity = new EntityMy();
    ReadFile rf = new ReadFile();
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String ID = request.getParameter("ID");
        String Date = request.getParameter("Date");
        String Note = request.getParameter("Note");
        String Button = request.getParameter("Button");
        
        LOG.info("ID: "+ID);
        LOG.info("Date: "+Date);
        LOG.info("Note: "+Note);
        LOG.info("Button: "+Button);
        
        switch (Button){
            case "Add":{
                LOG.info("Add a new note.");
                if(Date.trim().length()>0&&Note.trim().length()>0){
                    entity.setDt(Date);
                    entity.setNote(Note);
                    rf.add(entity);
                }
                else LOG.info("EMPTY PARAMETER!!!!!");
            }
            break;
            case "Edit":{
                LOG.info("Edit a note.");
                if(Date.trim().length()>0&&Note.trim().length()>0&&ID!=null){
                    entity.setDt(Date);
                    entity.setNote(Note);
                    rf.edit(entity, new Integer(ID));
                }
                else LOG.info("EMPTY PARAMETER!!!!!");            
            }
            break;
            case "Delete":{
                LOG.info("Delete a note.");
                if(Date.trim().length()>0&&Note.trim().length()>0&&ID!=null){
                    rf.delete(new Integer(ID));
                }
                else LOG.info("EMPTY PARAMETER!!!!!");       
            }
            break;    
            default: LOG.info("Other action.");
                
        }
               
        response.sendRedirect("index.jsp");
    }
}
