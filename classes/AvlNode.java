package classes;

public class AvlNode {
	protected int height; 
	protected String key;
	protected Dicionario element;
	protected AvlNode left, right;
	
	public AvlNode ( Dicionario theElement ) {
		this( theElement, null, null );
	}
	
	public AvlNode ( Dicionario theElement, AvlNode lt, AvlNode rt ) {
		element = theElement;
		key = theElement.getPalavra();
		left = lt;
		right = rt;
		height = 0;
	}
}
