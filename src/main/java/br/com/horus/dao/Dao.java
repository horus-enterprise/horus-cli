package br.com.horus.dao;


import org.apache.commons.dbcp2.BasicDataSource;

public class Dao {

    private BasicDataSource dataSource;

    public Dao() {
   
            this.dataSource = new BasicDataSource();
            this.dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.dataSource.setUrl("jdbc:sqlserver://horusdb.database.windows.net:1433;database=Horus");
            this.dataSource.setUsername("horus");
            this.dataSource.setPassword("#Gfgrupo7");
           
       
    }

    public BasicDataSource getDataSource() {
        return this.dataSource;
    }
}
