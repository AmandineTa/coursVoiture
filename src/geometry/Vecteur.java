package geometry;


public class Vecteur {
	public final double x,y;
	
	public Vecteur(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vecteur other = (Vecteur) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vecteur [x=" + x + ", y=" + y + "]";
	}
	
	public Vecteur add(Vecteur a){
		Vecteur b = new Vecteur(this.x +a.x,this.y +a.y);
		return b;
	}
	
	public Vecteur addb(double d){
		return new Vecteur(this.x+d,this.y+d);
	}
	
	public Vecteur sous(Vecteur a){
		Vecteur b = new Vecteur (this.x -a.x,this.y - a.y);
		return b;
	}
	
	
	public double scal(Vecteur v){
		return v.x*this.x+v.y*this.y;
	}
	
	public double vecto(Vecteur v){
		return v.y*this.x-v.x*this.y;
	}
	
	public Vecteur multi(double a){
		Vecteur v = new Vecteur(this.x*a, this.y*a);
		return v;
	}
	
	public double normescal(Vecteur v){
		return Math.sqrt(Math.pow((v.x-this.x),2) +Math.pow((v.y -this.y), 2));
	}
	
	public double norme(){
		return Math.sqrt(this.x*this.x+this.y*this.y);
	}
	
	public Vecteur rot(double r){
		Vecteur v = new Vecteur(this.x*Math.cos(r)-this.y*Math.sin(r),
				this.x*Math.sin(r)+this.y*Math.cos(r));
		return v;
	}
	
	public Vecteur clone(){
		Vecteur v = new Vecteur(this.x,this.y);
		return v;
	}
	
	public boolean egali(Vecteur v){
		if (this.x == v.x && this.y == v.y){
			return true;
		}
		return false;
	}
	
	public double angle(Vecteur v){
		double pro = this.scal(v)/this.norme()*v.norme();
		if(pro<-1){
			pro = -1.0;
		}
		if(pro >1){
			pro = 1.0;
		}
		double ang =  Math.signum(this.vecto(v))*Math.acos(pro);
		System.out.println(ang);
		return ang;
	}

	public Vecteur unitVec() {
		Vecteur v = new Vecteur(this.x/this.norme(), this.y/this.norme());
		return v;
	}

	public Vecteur fact(double vitesse) {
		Vecteur v = new Vecteur (this.x*vitesse,this.y*vitesse);
		return v;
	}
	
}