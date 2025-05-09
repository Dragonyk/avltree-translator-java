package classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
//CLASSE GUI DO TRADUTOR COM EXTENDS NO JFRAME E IMPLEMENTS NO ACTIONLISTENER PARA OS BOTÕES
public class GUI_Tradutor extends JFrame implements ActionListener{
	protected boolean isSave;
	static final String filename = "dicionario.dat";
	Tradutor trad;
	
	JLabel lb1,lb2;
	JTextField txt_input;
	JTextArea txt_output;
	JButton bt_trad,bt_save;
	
	// CONSTRUTOR DA CLASSE PARA GERAR O GUI DO TRADUTOR
	// Com definições de tamanho, titulo, paineis
	public GUI_Tradutor(){
		setSize(380,400);
		setTitle("Tradutor: en-US/pt-BR");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		
		//OPERAÇÂO DE SAIDA PARA VERIFICA SE FOI SALVO OU NÃO
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				if(isSave==false){
				    int confirmar = JOptionPane.showConfirmDialog(null, 
						   "O dicionario não foi salvo, deseja salva-lo agora?",
						   "Salvar e sair",
					        JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				    
				    if (confirmar == JOptionPane.YES_OPTION)
					    	save();
				}
				System.exit(0);
			}
		});
		
		isSave = true;
		trad = new Tradutor();
		carregarDicionario(filename);
		
		
		JPanel painel = new JPanel();
		
		painel.setLayout(new BoxLayout(painel,BoxLayout.Y_AXIS));
		painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		lb1 = new JLabel("Digite a palavra a ser traduzida:");
		//lb1.
		lb2 = new JLabel("Definições dessa palavra:");
		
		txt_input = new JTextField(10);
		txt_input.setText("Digite aqui...");
		txt_input.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				txt_input.setText("");
			}
		});
		
		txt_output = new JTextArea(10,10);
		txt_output.setEditable(false);
		txt_output.setBackground(Color.WHITE);
		txt_output.setLineWrap(true);
		txt_output.setAlignmentX(0);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		txt_output.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(0, 0, -10, -10)));
		
		bt_trad = new JButton("Traduzir");
		bt_trad.addActionListener(this);
		bt_trad.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		bt_save = new JButton("Salvar Dicionario");
		bt_save.addActionListener(this);
		bt_save.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		painel.add(lb1);
		painel.add(Box.createRigidArea(new Dimension(0,10)));
		painel.add(txt_input);
		painel.add(Box.createRigidArea(new Dimension(0,10)));
		painel.add(bt_trad);
		painel.add(Box.createRigidArea(new Dimension(0,10)));
		painel.add(lb2);
		painel.add(Box.createRigidArea(new Dimension(0,10)));
		painel.add(txt_output);
		painel.add(Box.createRigidArea(new Dimension(0,40)));
		painel.add(bt_save);
		painel.add(Box.createRigidArea(new Dimension(0,10)));
		
		getContentPane().add(painel);
	}
	
	//Método privado para carregar o dicionario se possivel
	//Senão exibe uma mensagem em JOptionPane
	private void carregarDicionario(String arq){
		try {
			trad.carregaDicionario(arq);
		} 
		catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar carregar o dicionario!");
			System.exit(0);
		}
	}
	
	// Método para salvar o dicionario chamando o método salvarDicionario da classe Tradutor 
	// com o parametro filenome que é a constante do String "dicionario.dat"
	public void save(){
		trad.salvaDicionario(filename);
		isSave=true;
		JOptionPane.showMessageDialog(null, "O dicionario foi salvo com sucesso!");
	}
	
	//Método para traduzir uma palavra expecificada no JTextField txt_input e mostrando as definições no JTextArea txt_output
	//Se não houver defenições para essa palavra então vai oferecer a possibilidade de adicionar definições para a mesma.
	public void traduzir(){
		String str = txt_input.getText();
		List<String> lista = trad.traduzPalavra(str);
		String defs = "";
		String error = "Não foi encontrada nenhuma definição.";
		if(lista!=null){
			defs = lista.get(0);
			for(int i=1;i<lista.size();i++){
				defs+= ", "+lista.get(i);
			}
			
			txt_output.setText(defs);
		}
		else{
			txt_output.setText(error);
			String msg = "Não foi encontrada uma tradução para \""+str+"\"\nDeseja adicianar possiveis traduções para essa palavra?";
			int conf = JOptionPane.showConfirmDialog(null, msg, "\""+str+"\" não tem tradução!", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			
			if(conf==JOptionPane.YES_OPTION){
				String palavras;
				do{
				palavras = JOptionPane.showInputDialog(null,"Inserindo definições para a palavra "+str+"\nSepare as palavras por virgula ( , ) ou ponto e virgula ( ; )","Inserindo definições"+str,JOptionPane.PLAIN_MESSAGE);
				}while(!stringIsOk(palavras)&&palavras!=null);
				
				if(palavras!=null){
					palavras = palavras.replaceAll("\\s+","");
					String[] p_array = palavras.split(",|\\;");
					
					List<String> definicoes = new LinkedList<String>();
					for(int i=0;i<p_array.length;i++){
						definicoes.add(p_array[i]);
					}
					
					trad.insereTraducao(str, definicoes);
					isSave = false;
				}
				traduzir();
			}
		}
	}
	
	//Método simples para verificar a String de definições do método traduzir
	public boolean stringIsOk(String s){
		if(s.trim().equals("")) return false;
		if(s.charAt(s.length()-1)==',' ||
		   s.charAt(s.length()-1)==';'){
			return false;
		} 
		
		return true;
	}

	//Método main para gerar o tradutor
	public static void main(String[] args) {
		GUI_Tradutor t = new GUI_Tradutor();
		t.pack();
		t.setVisible(true);
	}
	
	//Método da classe ActionListener que foi implementada para definir evento de botões
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bt_trad){
			traduzir();
		}
		if(e.getSource()==bt_save){
			save();
		}
	}

}
