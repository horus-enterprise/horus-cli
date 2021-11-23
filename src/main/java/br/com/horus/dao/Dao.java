package br.com.horus.dao;

import br.com.horus.utils.Logger;
import java.io.IOException;
import org.apache.commons.dbcp2.BasicDataSource;

public class Dao {
    
    private BasicDataSource dataSource;
    
    public Dao() {
        try {
            this.dataSource = new BasicDataSource();
            this.dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.dataSource.setUrl("jdbc:sqlserver://horusdb.database.windows.net:1433;database=Horus");
            this.dataSource.setUsername("horus");
            this.dataSource.setPassword("#Gfgrupo7");
            Logger.escreverLogger("> Conectado ao servidor: jdbc:sqlserver://horusdb.database.windows.net:1433;database=Horus" +Logger.geradorDatas());
        } catch (IOException e) {
            Logger.loggerException(e);
            
        }
    }
    
    public BasicDataSource getDataSource() {
        return this.dataSource;
    }
}
