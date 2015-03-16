package application;

import java.io.*;
import java.util.ArrayList;
import org.apache.log4j.*;

public class ReadFile {
    private static final Logger LOG = Logger.getLogger(ReadFile.class);
     
    public String dir = Config.DIR;
    public String fileName = Config.FILE_NAME;
          
    public BufferedReader reader;
    public BufferedWriter writer;
    public ArrayList<EntityMy> entityList;
    
    public ArrayList<EntityMy> readFile(){
        LOG.info("dir: "+dir);
        LOG.info("fileName: "+fileName);
        LOG.info("Read a file.");
        entityList= new ArrayList<EntityMy>();
        try {
          reader = new BufferedReader(new FileReader(dir+fileName));
          int a;
          EntityMy entity;
          String line;
          while((line=reader.readLine())!=null){
                entity = new EntityMy();
                a=0;               
                for(int j=0; j<line.length(); j++){
                    if(line.charAt(j)==';')a=j;
                    entity.setDt(line.substring(0, a));
                    entity.setNote(line.substring(a+1,line.length()));
                }
                entityList.add(entity);
            }
          reader.close();
          return entityList;
      } catch (FileNotFoundException ex) {
          java.util.logging.Logger.getLogger(ReadFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      } catch (IOException ex) {
          java.util.logging.Logger.getLogger(ReadFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      return null;
    }
    
    public Boolean writeFile(ArrayList<EntityMy> entityList){
      try {
          LOG.info("Write a file.");
          writer = new BufferedWriter(new FileWriter(dir+fileName));
          String str;
          for(EntityMy e: entityList){
              str = e.getDt()+";"+e.getNote();
              writer.write(str);
              writer.write('\n');
          }
          writer.close();
          return true;
      } catch (IOException ex) {
          java.util.logging.Logger.getLogger(ReadFile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
      }
      return false;
    }
        
    public Boolean add(EntityMy entity){
      LOG.info("Method add");
      entityList=this.readFile();
      entityList.add(entity);
      return this.writeFile(entityList);
    }
    
    public Boolean edit(EntityMy e, Integer ID){
      LOG.info("Method edit");
      LOG.info("Date: "+e.getDt());
      LOG.info("Note: "+e.getNote());
      entityList = this.readFile();
      entityList.set(ID, e);
      return this.writeFile(entityList);
    }
    
    public Boolean delete (Integer nID) throws IOException{
      LOG.info("Method delete");
      LOG.info("ID: "+nID);
      entityList = this.readFile();
      entityList.remove(entityList.get(nID));
      return this.writeFile(entityList);
    }
}