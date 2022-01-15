package mains;

import geometry.Vecteur;


public class VecteureTeste {
	public static void main(String[] args){
		Vecteur v1 = new Vecteur(0,1);
		Vecteur v2 = new Vecteur(2,2);
		Vecteur v3 = new Vecteur (-1,-3);
		Vecteur v4 = new Vecteur(1,3);
		v3 = v1.add(v4);
		System.out.println(v3.toString());
		
		Vecteur v5 = new Vecteur(1,0);
		v3 = v5.rot(Math.PI/2);
		System.out.println(v3.toString());
		
		Vecteur v6 = new Vecteur(1,1);
		v3 = v6.rot(Math.PI/2);
		System.out.println(v3.toString());
		
		System.out.println(v1.scal(v5));
		System.out.println(v2.vecto(v6));
		System.out.println(v3.scal(v4));
		
	}
}
