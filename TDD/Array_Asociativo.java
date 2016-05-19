
public class Array_Asociativo extends Object {

	public class Nodo{
		public String clave;
		public String valor;
		public Nodo sig;
		public Nodo(){
		clave=null;
		valor=null;
		sig=null;
		}
	}
	
	Nodo act;
	public Array_Asociativo(){
		act=new Nodo();
		act.sig=null;
	}
	
	
	public void put(String c, String v){
		Nodo nuevo=new Nodo();
		if(act.clave==null){
			act.clave=c;
			act.valor=v;
		}else{
			nuevo.clave=c;
			nuevo.valor=v;
			nuevo.sig=null;
			act.sig=nuevo;
		}
		
	}
	
	public String get(String clave){
		Nodo aux=new Nodo();
		aux=act;
		while(aux.clave!=clave&&aux!=null){
			aux=aux.sig;
		}
		return aux.valor;
	}
	
	public String getOrElse(String clave, String defecto){
		String res = defecto;
		if(this.get(clave)!=null){
			res=this.get(clave);
		}
		return res;
	}
	
	public int size(){
		Nodo aux = new Nodo();
		int res=0;
		while(aux!=null){
			res++;
		}
		return res;
	}
	
	public boolean remove(String clave){
		Nodo current=new Nodo();
		Nodo ant=new Nodo();
		ant=act;
		current=ant.sig;
		boolean res=false;
		if(ant.clave==clave){
			res=true;
			act.sig=null;
		}else {
			while(current.clave!=clave&&current.clave!=null){
				ant=ant.sig;
				current=current.sig;
			}
			if(current!=null){
				current=current.sig;
				res=true;
				ant.sig=current;
			}
		}
		return res;
	}
}
