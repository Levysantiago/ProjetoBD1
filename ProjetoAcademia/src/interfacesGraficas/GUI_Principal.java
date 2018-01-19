package interfacesGraficas;

import banco_dados.Conector;
import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.OutputChangeEvent;
import com.phidgets.event.OutputChangeListener;
import com.phidgets.event.TagGainEvent;
import com.phidgets.event.TagGainListener;
import com.phidgets.event.TagLossEvent;
import com.phidgets.event.TagLossListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author levy
 */
public final class GUI_Principal extends javax.swing.JFrame {
    
    private RFIDPhidget rfid;
    private boolean ligado;
    private Cadastro pagCadastro;
    private Pagamento pagPagamento;
    private int idAtualCliente;
    
    public GUI_Principal() {
        initComponents();
        this.rfid = null;
        this.ligado = false;
        this.idAtualCliente = -1;
        this.inicializarCampos();
        
        this.setLocationRelativeTo(null); //INICIAR O JFRAME NO MEIO DA TELA
        
        this.ligado = false;
        
        //EVENTO PARA O BOTÃO SAIR
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(ligado){
                    try {
                        desligaRFID();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Desligue o dispositivo antes de sair.");
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_ctr = new javax.swing.JPanel();
        lb_imgStatus = new javax.swing.JLabel();
        btn_cadastrar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btn_pagar = new javax.swing.JButton();
        tb_ligdeslig = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        tf_nome = new javax.swing.JTextField();
        tf_sobrenome = new javax.swing.JTextField();
        tf_status = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tf_codigo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_ctr.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Painel de Controle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 12))); // NOI18N

        btn_cadastrar.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn_cadastrar.setText("Cadastrar");
        btn_cadastrar.setPreferredSize(new java.awt.Dimension(55, 23));
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel8.setText("Nome:");

        btn_pagar.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        btn_pagar.setText("Pagamento");
        btn_pagar.setMaximumSize(new java.awt.Dimension(39, 17));
        btn_pagar.setMinimumSize(new java.awt.Dimension(39, 17));
        btn_pagar.setPreferredSize(new java.awt.Dimension(55, 23));
        btn_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagarActionPerformed(evt);
            }
        });

        tb_ligdeslig.setText("Ligar");
        tb_ligdeslig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_ligdesligActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel9.setText("Status:");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel10.setText("Sobrenome:");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel11.setText("Código:");

        javax.swing.GroupLayout jPanel_ctrLayout = new javax.swing.GroupLayout(jPanel_ctr);
        jPanel_ctr.setLayout(jPanel_ctrLayout);
        jPanel_ctrLayout.setHorizontalGroup(
            jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ctrLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ctrLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(btn_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel_ctrLayout.createSequentialGroup()
                        .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_ctrLayout.createSequentialGroup()
                                .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_ctrLayout.createSequentialGroup()
                                        .addGap(116, 116, 116)
                                        .addComponent(tb_ligdeslig, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ctrLayout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addComponent(lb_imgStatus))))
                                    .addGroup(jPanel_ctrLayout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(tf_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ctrLayout.createSequentialGroup()
                                        .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel11))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tf_status)
                                            .addComponent(tf_codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                            .addComponent(tf_sobrenome, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(0, 57, Short.MAX_VALUE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        jPanel_ctrLayout.setVerticalGroup(
            jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ctrLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tf_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_sobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tf_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf_status, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 19, Short.MAX_VALUE)
                .addGroup(jPanel_ctrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ctrLayout.createSequentialGroup()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_imgStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tb_ligdeslig, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_ctr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_ctr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_ligdesligActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_ligdesligActionPerformed
        try {
            if(ligado == true){
                this.desligaRFID();
                if(Conector.finalizarConeccao()){
                    System.out.println("Banco de dados Desconectado!");
                    ligado = false;
                    //Zerando os textFilds
                    inicializarCampos();
                    tb_ligdeslig.setText("Ligar");                    
                    tb_ligdeslig.setSelected(false);
                }else{
                    System.out.println("Falha ao desconectar Banco de Dados!");
                }                
            }else{                
                if(Conector.iniciarConexao()){
                    System.out.println("Banco de dados Conectado!");
                    this.ligaRFID();
                    ligado = true;
                    tb_ligdeslig.setText("Desligar");
                    tb_ligdeslig.setSelected(true);
                }else{
                    tb_ligdeslig.setSelected(false);
                    JOptionPane.showMessageDialog(this, "Falha na Conexão com o Banco de Dados! Talvez o usuário não é permitido, ou o banco não foi inicializado.");
                }
            }
        } catch (Exception ex) {
            //Se o dispositivo não está conectado, reiniciamos a variavel rfid
            try {
                rfid.close();
            } catch (PhidgetException ex1) {
                Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(this, "Conecte o dispositivo por favor.");
            Conector.finalizarConeccao();
            System.out.println("Banco de dados Desconectado!");
            tb_ligdeslig.setSelected(false);
            ligado = false;
        }
    }//GEN-LAST:event_tb_ligdesligActionPerformed

    private void btn_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagarActionPerformed
        this.setEnabled(false);
        pagPagamento = new Pagamento(this);
        pagPagamento.setVisible(true);        
    }//GEN-LAST:event_btn_pagarActionPerformed

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        String tag = tf_codigo.getText();
        this.setEnabled(false);        
        pagCadastro = new Cadastro(this);
        pagCadastro.setTfTag(tag);
        pagCadastro.setVisible(true);
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RFIDPhidget rfid;
                try {
                    rfid = new RFIDPhidget();
                    new GUI_Principal().setVisible(true);
                } catch (PhidgetException ex) {
                    Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    /*DESLIGA A ANTENA E LED DO LEITOR RFID*/
    public void desligaRFID()throws Exception{
        if(ligado){
            System.out.print("closing...");
            rfid.setAntennaOn(false);
            rfid.setLEDOn(false);
            rfid.close();
            rfid = null;
            System.out.println(" ok");
            System.out.println("wait for finalization...");
            System.gc();
            ligado = false;
            System.out.println("Done!");
            System.out.println("\n__________________________________________________________\n");
        }else{
            JOptionPane.showMessageDialog(this,"O dispositivo já está desligado!");
        }
    }//fim_desligaRFID
    
    /*LIGA A ANTENA E LED DO LEITOR RFID E IMPLEMENTA OS EVENTOS DE LEITURA DE CÓDIGO
      OS EVENTOS DE LEITURA SÃO CHAMADOS PELO MÉTODO MAIN DA CLASSE RFID*/
    public void ligaRFID()throws Exception{
        if(ligado){
            JOptionPane.showMessageDialog(this,"O dispositivo já está ligado!");
        }else{
            rfid = new RFIDPhidget();
                        
            System.out.println(Phidget.getLibraryVersion());
            
            //EVENTOS
            rfid.addAttachListener(new AttachListener() {
                @Override
                public void attached(AttachEvent ae)
                {
                    try
                    {
                        ((RFIDPhidget)ae.getSource()).setAntennaOn(true);
                        ((RFIDPhidget)ae.getSource()).setLEDOn(true);
                    }
                    catch (PhidgetException ex) { }
                    System.out.println("attachment of " + ae);
                }
            });
            rfid.addDetachListener(new DetachListener() {
                @Override
                public void detached(DetachEvent ae) {
                    System.out.println("detachment of " + ae);
                }
            });
            rfid.addErrorListener(new ErrorListener() {
                @Override
                public void error(ErrorEvent ee) {
                    System.out.println("error event for " + ee);
                }
            });
            
            //EVENTO DE LEITURA DA TAG
            rfid.addTagGainListener(new TagGainListener()
            {
                @Override
                public void tagGained(TagGainEvent oe)
                {
                    inicializarCampos();
                    String query, aux;
                    String tag = oe.getValue();
                    ArrayList id_cliente;
                    ArrayList result;
                    Date dataAtual, dataCadastrada;
                                        
                    tf_codigo.setText(oe.getValue());
                    try{
                        //SELECIONANDO TAG DO BD
                        query = "SELECT TAG_CLI_ID FROM TBL_TAG_RFID WHERE TAG_VALOR = '"+tag+"';";
                        id_cliente = Conector.selecionar(query, "TAG_CLI_ID");
                        if(id_cliente.size() < 1){
                            //LIBERAR BOTAO CADASTRAR
                            tf_status.setText("Não cadastrado");
                            btn_cadastrar.setEnabled(true);
                        }else{ //ESTÁ CADASTRADO                           
                            idAtualCliente = Integer.parseInt((String) id_cliente.get(0));
                                    
                            //OBTENDO O NOME E SOBRENOME DO CLIENTE CORRESPONDENTE AO CODIGO
                            query = "SELECT CLI_NOME FROM TBL_CLIENTE WHERE CLI_ID = "+idAtualCliente+";";
                            result = Conector.selecionar(query, "CLI_NOME");
                            tf_nome.setText((String) result.get(0));
                            query = "SELECT CLI_SOBRENOME FROM TBL_CLIENTE WHERE CLI_ID = "+idAtualCliente+";";
                            result = Conector.selecionar(query, "CLI_SOBRENOME");
                            tf_sobrenome.setText((String) result.get(0));
                            
                            //VERIFICANDO SE A DATA DO PAGAMENTO VENCEU
                            query = "SELECT TO_CHAR(PAG_VENCIMENTO, 'DD/MM/YYYY') FROM TBL_PAGAMENTOS WHERE PAG_CLI_ID = "
                                    + idAtualCliente +" and PAG_PAGO = 'f';";
                            
                            //Obtendo data cadastrada
                            result = Conector.selecionar(query, "to_char");
                            
                            //Criando um formatador de datas
                            SimpleDateFormat formatador = new SimpleDateFormat("dd/mm/yyyy");
                            
                            //Obtendo data do servidor
                            aux = Conector.getDataServidor();
                            
                            //Formatando para util.Date
                            dataAtual = new Date(formatador.parse(aux).getTime());                                                        
                            
                            //Formatando data cadastrada
                            aux = (String) result.get(0);
                            dataCadastrada = new Date(formatador.parse(aux).getTime());                            
                            
                            //Tratando se a data atual está no prazo de pagamento
                            if(dataAtual.after(dataCadastrada)){
                                //Data de pagamento vencida
                                query = "UPDATE TBL_CLIENTE SET CLI_ATIVO = FALSE WHERE CLI_ID = "+idAtualCliente;
                                Conector.atualizar(query);
                                tf_status.setText("Pagamento Vencido");
                                btn_pagar.setEnabled(true);
                            }else{
                                tf_status.setText("Entrada Permitida");
                            }                            
                        }
                        System.out.println("Tag Gained: " +oe.getValue() + " (Proto:"+ oe.getProtocol()+")");
                    } catch (Exception ex) {
                        Logger.getLogger(GUI_Principal.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "Erro ao recuperar dados do BD.");
                        inicializarCampos();
                    }
                }
            });
            
            rfid.addTagLossListener(new TagLossListener()
            {
                @Override
                public void tagLost(TagLossEvent oe)
                {
                    System.out.println(oe);
                }
            });
            rfid.addOutputChangeListener(new OutputChangeListener()
            {
                @Override
                public void outputChanged(OutputChangeEvent oe)
                {
                    System.out.println(oe);
                }
            });
            //fim_EVENTOS
            
            //Utilizando os eventos para ligar o leitor
            rfid.openAny();
            System.out.println("waiting for RFID attachment...");
            rfid.waitForAttachment(1000);
            
            System.out.println("Serial: " + rfid.getSerialNumber());
            System.out.println("Outputs: " + rfid.getOutputCount());

            //How to write a tag:
            //rfid.write("A TAG!!", RFIDPhidget.PHIDGET_RFID_PROTOCOL_PHIDGETS, false); 

            System.out.println("Outputting events.  Input to stop.");
        }
        
    }//fim_ligaRFID
    
    //Método utilizado para chamar o evento de leitura do código rfid
    public void lerTag(){
        new TagGainListener()
        {
            @Override
            public void tagGained(TagGainEvent oe)
            {
                
            }
        };
    }
    
    /**
     * Inicializa todos os campos desta JFrame.
     */
    public void inicializarCampos(){
        tf_nome.setText("");
        tf_nome.setEditable(false);
        tf_sobrenome.setText("");
        tf_sobrenome.setEditable(false);
        tf_codigo.setText("");
        tf_codigo.setEditable(false);
        tf_status.setText("");
        tf_status.setEditable(false);
        btn_cadastrar.setEnabled(false);
        btn_pagar.setEnabled(false);
    }
    
    //MÉTODOS GET
    public RFIDPhidget getRFID(){
        return rfid;
    }
    
    public boolean getLigado(){
        return ligado;
    }
    
    public void setIdAtualCliente(int id){
        this.idAtualCliente = id;
    }
    
    public int getIdAtualCliente(){
        return this.idAtualCliente;
    }

    public String getTf_codigoTxt() {
        return tf_codigo.getText();
    }

    public String getTf_nomeTxt() {
        return tf_nome.getText();
    }

    public String getTf_sobrenomeTxt() {
        return tf_sobrenome.getText();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_pagar;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel_ctr;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb_imgStatus;
    private javax.swing.JToggleButton tb_ligdeslig;
    private javax.swing.JTextField tf_codigo;
    private javax.swing.JTextField tf_nome;
    private javax.swing.JTextField tf_sobrenome;
    private javax.swing.JTextField tf_status;
    // End of variables declaration//GEN-END:variables
}
