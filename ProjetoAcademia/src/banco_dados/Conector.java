package banco_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import projetoacademia.Cliente;

/**
 *
 * @author levy
 */
public class Conector {
    private static Connection con = null;
    
    /**
     * Inicia uma conexão com o banco de dados.
     * @return <code>true</code> Se a conexão foi estabelecida com sucesso.<br>
     *         <code>false</code> Se ouve algum problema ou se já existia uma conexão.
     */
    public static boolean iniciarConexao(){
        //ESTABELECENDO CONEXÃO COM O BANCO DE DADOS        
        if(con != null){
            return false;
        }
        try{            
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/academiaDB","funcionario","123456");
            return true;
        }catch (SQLException | ClassNotFoundException e) {
            return false;
        }
    }//fim_iniciarConeccao
    
    /**
     * Finaliza a conexão com o banco.
     * @return <code>true</code> Se a conexão foi finalizada com sucesso.<br>
     *         <code>false</code> Se ouve algum problema ou já não existia conexão.
     */
    public static boolean finalizarConeccao(){
        if(con == null){
            return false;
        }
        try{
            con.close();    
            con = null;
            return true;
        } catch(SQLException e){
            return false;
        }    
    }//fim_finalizarConeccao
    
    /**
     * Solicita a data atual ao Banco de Dados.
     * @return A data atual em forma de <code>String</code>.
     *         <code>null</code> Se o BD não está conectado.
     * @throws Exception 
     */
    public static String getDataServidor() throws Exception{
        PreparedStatement stmt;
        String query;
        String data;
        
        if(con == null){
            return null;
        }
        query = "SELECT TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY')";
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();       
        rs.next();
        data = rs.getString("to_char");
        stmt.close();
        
        return data;
    }
    
    /**
     * Solicita ao Banco de Dados a data de hoje com o mês somado de 1.
     * @return A data calculada em forma de <code>String</code>
     *         <code>null</code> Se o BD não está conectado.
     * @throws Exception 
     */
    public static String getDataProxMes() throws Exception{
        PreparedStatement stmt;
        String query;
        String data;
        
        if(con == null){
            return null;
        }        
        query = "SELECT TO_CHAR(CURRENT_DATE + INTERVAL '1 MONTH', 'DD/MM/YYYY')";
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        data = rs.getString("to_char");
        stmt.close();
        
        return data;
    }
    
    /**
     * Obtém do Banco de Dados valor total de um certo contrato.
     * @param idContrato O identificador do contrato no Banco.
     * @return O valor total do contrato.
     *         <code>-1</code> Se o BD não está conectado.
     * @throws Exception 
     */
    public static double getValorContrato(int idContrato) throws Exception{
        double valor = 0;
        PreparedStatement stmt;
        String query;
        
        if(con == null){
            return -1;
        }
        query = "SELECT SUM(S.SERV_VALOR) FROM "
                + "TBL_CONTR_SERV AS CS "
                + "JOIN TBL_SERVICOS AS S "
                + "ON CS.CV_SERV_ID = S.SERV_ID "
                + "WHERE CS.CV_CONTR_ID = "+idContrato+";";
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            valor = rs.getDouble(1);
        }            
        stmt.close();
        
        return valor;
    }
    
    /**
     * Solicita ao banco o identificador do contrato com um certo cliente.
     * @param idCliente o identificador do cliente no BD.
     * @return O id do contrato no BD.
     *         <code>-1</code> Se o BD não está conectado.
     * @throws Exception 
     */
    public static int getIdContrato(int idCliente) throws Exception{
        PreparedStatement stmt;
        int idContrato;
        String query;
        
        if(con == null){
            return -1;
        }
        query = "SELECT CONTR_ID FROM TBL_CONTRATO "
                        + "WHERE CONTR_CLI_ID = "+idCliente+";";
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        idContrato = rs.getInt(1);
        stmt.close();
        
        return idContrato;
    }
    
    /**
     * Executa uma query para Select no BD.
     * @param query O código do select.
     * @param coluna A coluna a qual o resultado será obtido.
     * @return Uma lista obtendo todas as tuplas do resultado do select para a coluna dada. 
     *         <code>null</code> Se o BD não está conectado.
     * @throws Exception 
     */
    public static ArrayList selecionar(String query,String coluna)throws Exception{
        PreparedStatement stmt;
        ArrayList lista;
        
        if(con == null){
            return null;
        }
        lista = new ArrayList();
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            lista.add(rs.getString(coluna));
        }
        stmt.close();
        return lista;
    }//fim_selecionaCodigos
    
    /**
     * Executa uma query para Updates e Inserts no BD.
     * @param query O código do update/insert.
     * @return <code>true</code> Se o código foi executado com sucesso.<br>
     *         <code>false</code> Se o BD não está conectado.
     * @throws Exception 
     */
    public static boolean atualizar(String query)throws Exception{
        PreparedStatement stmt;
        
        if(con == null){
            return false;
        }
        stmt = con.prepareStatement(query); //Preparando o comando
        
        //Executando o comando
        stmt.executeUpdate();
        stmt.close();
        return true;
    }//fim_inserir
    
    /**
     * Atualiza a tabela pagamentos com o próximo pagamento a ser feito.
     * @param clienteId O id do cliente no BD.
     * @param idFormPag O id da forma de pagamento escolhida.
     * @return <code>true</code> Se a atualização foi um sucesso.<br>
     *         <code>false</code> Se o BD não está conectado.
     * @throws Exception 
     */
    public static boolean atualizarDataPagamento(int clienteId, int idFormPag) throws Exception{
        String proxData;
        String query;
        int contratoId;
        
        if(con == null){
            return false;
        }
        proxData = getDataProxMes();
        
        //OBTENDO OS INDICES DO CONTRATO
        contratoId = getIdContrato(clienteId);
        
        //INSERINDO PRÓXIMO PAGAMENTO A SER FEITO
        query = "INSERT INTO TBL_PAGAMENTOS (PAG_VENCIMENTO, PAG_CONTR_ID,"
                + " PAG_CLI_ID, PAG_FORM_ID) VALUES ('"+ proxData +"', "
                + contratoId +", "+clienteId+", "+idFormPag+");";
        atualizar(query);
        return true;
    }
    
    /**
     * Insere os dados de um novo cliente que está sendo cadastrado.
     * @param cliente O cliente que está sendo cadastrado.
     * @return <code>true</code> Se o cliente foi cadastrado com sucesso.<br>
     *         <code>false</code> Se o BD está desconectado.
     * @throws Exception 
     */
    public static boolean inserirCliente(Cliente cliente) throws Exception{
        PreparedStatement stmt;
        ResultSet rs;
        int clienteId, contratoId;
        String data;
        List<String> lista;
        int idFormPag;
        
        if(con == null){
            return false;
        }
        
        //Guardando a data atual
        data = getDataServidor();
        
        //INSERINDO NA TABELA CLIENTE
        String query = "INSERT INTO TBL_CLIENTE "
                        + "(CLI_NOME, CLI_SOBRENOME, CLI_CPF, "
                        + "CLI_RG, CLI_EMAIL, CLI_ATIVO) VALUES "
                        + "('"+cliente.getNome()+"', '"+cliente.getSobrenome()+"', "
                        + "'"+cliente.getCpf()+"', '"+cliente.getRg()+"', "
                        + cliente.getEmail()+", "+cliente.isAtivo()+");";        
        //Preparando o comando
        stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        //Executando o comando
        stmt.executeUpdate();
        
        rs = stmt.getGeneratedKeys();
        rs.next();
        clienteId = rs.getInt(1);
        
        //INSERINDO NA TABELA ENDERECO        
        query = "INSERT INTO TBL_ENDERECO (END_LOGRADOURO, END_CEP, END_NUMERO, "
                + "END_APTO, END_COMPLEMENTO, END_CLI_ID) VALUES"
                + " ('"+cliente.getLogradouro()+"', "+cliente.getCep()+", "
                + "'"+cliente.getNumero()+"', "+cliente.getApto()+", "
                + cliente.getComplemento()+", "+clienteId+");";        
        //Preparando o comando
        stmt = con.prepareStatement(query);        
        //Executando o comando
        stmt.executeUpdate();
        
        //INSERINDO NA TABELA TELEFONE
        query = "INSERT INTO TBL_TELEFONES (TEL_RESIDENCIAL, TEL_CELULAR, TEL_CLI_ID) VALUES"
                + " ('"+cliente.getTel()+"', "+cliente.getCel()+", "+clienteId+");";        
        //Preparando o comando
        stmt = con.prepareStatement(query);        
        //Executando o comando
        stmt.executeUpdate();
        
        //INSERINDO NA TABELA TAG_RFID
        query = "INSERT INTO TBL_TAG_RFID (TAG_VALOR, TAG_DESCRICAO, TAG_CLI_ID) VALUES"
                + " ('"+cliente.getTag()+"', "+cliente.getDescrTag()+", "+clienteId+");";        
        //Preparando o comando
        stmt = con.prepareStatement(query);        
        //Executando o comando
        stmt.executeUpdate();
        
        //INSERINDO NA TABELA CONTRATO        
        query = "INSERT INTO TBL_CONTRATO (CONTR_DATA_INICIO, CONTR_CLI_ID) VALUES"
                + " ('"+data+"', "+clienteId+");";
        
        //Preparando comando
        stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);        
        //Executando o comando
        stmt.executeUpdate();
        
        rs = stmt.getGeneratedKeys();
        rs.next();
        contratoId = rs.getInt(1);
        
        
        //SELECIONANDO DA TABELA FORMA DE PAGAMENTO
        query = "SELECT FORM_ID FROM TBL_FORMA_PAG WHERE FORM_DESCRICAO = '"+cliente.getFormaPag()+"';";        
        //Preparando o comando
        stmt = con.prepareStatement(query);        
        //Executando o comando
        rs = stmt.executeQuery();
        
        rs.next();
        idFormPag = rs.getInt(1);        
        
        //INSERINDO NA TABELA PAGAMENTOS        
        data = getDataProxMes();
        
        query = "INSERT INTO TBL_PAGAMENTOS (PAG_VENCIMENTO, PAG_PAGO, PAG_CONTR_ID,"
                + " PAG_CLI_ID, PAG_FORM_ID) VALUES ('"+ data +"', false, "
                + contratoId +", "+clienteId+", "+idFormPag+");";
              
        //Preparando o comando
        stmt = con.prepareStatement(query);        
        //Executando o comando
        stmt.executeUpdate();
        
        //SELECIONANDO OS SERVIÇOS ESCOLHIDOS
        query = "SELECT SERV_ID FROM TBL_SERVICOS WHERE";
        lista = cliente.getServicos();        
        for(int i = lista.size()-1; i > -1; i--){
            query += " SERV_DESCRICAO =  '"+lista.get(i)+"'";
            if(i - 1 > -1){
                query += " OR";
            }
        }
        query += ";";        

        //Preparando o comando
        stmt = con.prepareStatement(query);        
        //Executando o comando
        rs = stmt.executeQuery();
        
        lista.removeAll(lista);
        while(rs.next()){
            lista.add(rs.getString("SERV_ID"));   
        }        
        
        //INSERINDO NA TABELA CONTRATOS E SERVIÇOS
        query = "";
        for(String idIterator : lista){
            query += "INSERT INTO TBL_CONTR_SERV (CV_CONTR_ID , CV_SERV_ID) VALUES ("+ contratoId +", "+ idIterator +");";            
        }
        
        //Preparando o comando
        stmt = con.prepareStatement(query);        
        //Executando o comando
        stmt.executeUpdate();        
        stmt.close();
        
        return true;
    }
}
