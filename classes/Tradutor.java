package classes;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Tradutor {
	protected AvlTree tree;
	
	public Tradutor(){
		tree = new AvlTree();
	}
	
	/*
	 * Carrega um arquivo denominado com a String "arq" fazendo a leitura linha por linha,
	 * gravando cada linha em um array, pegando o primeiro elemento do array e definindo como chave
	 * e pegando o resto dos elementos do array inserindo em uma lista encadeada(LinkedList).
	 * Então é criado uma classe Dicionario com os valores da linha e inserida na arvore.
	*/ 
	protected void carregaDicionario(String arq) throws IOException{
		FileReader reader = new FileReader(new File(arq));
		BufferedReader leitor = new BufferedReader(reader);  
		String linha = leitor.readLine();
		
		while(linha!=null){
			//System.out.println(linha);
			String[] array = linha.split("#");
			if(array.length>=2){
				List<String> list = new LinkedList<String>();
				for(int i=1; i<array.length;i++){
					list.add(array[i]);
				}
				tree.insert(new Dicionario(array[0], list));
			}
			
			linha=leitor.readLine();
		}
		leitor.close();
	}
	/*
	 * Recebe uma palavra chave como parametro então,
	 * Então verifica, se a arvore estiver vazia retorna null.
	 * Faz uma busca na arvore, se a palavra não for encontrada retorna null senão,
	 * retorna a lista de definições dessa palavra
	 * 
	*/
	public List<String> traduzPalavra(String palavra){
		if(tree.isEmpty()) return null;
		if(tree.search(palavra)==null) return null;
		
		return tree.search(palavra).element.definicoes;
	}
	/*
	 * Recebe uma palavra e uma lista de definições como parametros
	 * verifica se a lista esta vazia, se estiver cai em Exception,
	 * senão é criado um objeto Dicionario com a palavra e a lista
	 * e inserido na arvore(tree.insert).
	*/
	public void insereTraducao(String palavra, List<String> definicoes){
		if(definicoes.isEmpty()) return;
		
		tree.insert(new Dicionario(palavra, definicoes));
	}
	
	/*
	 * Chama recursivamente a classe privada salvaDicionario colocando como parametros o nome do arquivo,
	 * a raiz da arvore e o Bufferedwriter para gravar as informações no arquivo.
	*/
	public void salvaDicionario(String arq){
		try {
			FileWriter writer = new FileWriter(new File(arq));
			BufferedWriter out = new BufferedWriter(writer);
			salvaDicionario(arq,tree.getRootNode(),out);
			out.close();
			writer.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * salvaDicionario grava os elementos da arvore EM ORDEM
	 * Verifica se o node(prev) da arvore não é null, se for, encerra o metodo e volta recurcivamente.
	 * Senão, chama esse mesmo metodo recursivamente com o node a esquerda como parametro e repetindo isso até q chegue
	 * no final da esquerda e grave os valores das chaves(prev.key) e assim o mesmo processo se repete
	 * até gravar os valores da direita de forma recursiva.
	 *  
	*/
	private void salvaDicionario(String arq, AvlNode prev,BufferedWriter out){
		try {
			
			
			if(prev!=null){
				salvaDicionario(arq, prev.left,out);
				
				out.write(prev.key);
				for(int i=0;i<prev.element.definicoes.size();i++)
					out.write("#"+prev.element.definicoes.get(i));
				out.newLine();
				
				//System.out.print(p.key+" - ");
				salvaDicionario(arq, prev.right,out);
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
