package projetJavaS3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.Serializable;

/**
 * 
 * @author thief
 *
 */
public abstract class Creneau implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7858606967284234782L;
	/**
	 * 
	 */
	private int heured;
	/**
	 * 
	 */
	private int heuref;
	/**
	 * 
	 */
	private int mind;
	/**
	 * 
	 */
	private int minf;
	
	/**
	 * 
	 */
	public Creneau () {
		this.heured = -1;
		this.heuref = -1;
		this.mind = -1;
		this.minf = -1;
		
	}
	
	/**
	 * 
	 * @param dbth
	 * @param dbtm
	 * @param finh
	 * @param finm
	 * @throws CreneauHoraireException
	 */
	public Creneau (int dbth, int dbtm, int finh, int finm) throws CreneauHoraireException {
		if (dbth >= 0 && dbth <= 24) {
			this.heured = dbth;
		} else {
			this.heured = -1;
			throw new CreneauHoraireException(dbth);
		}
		
		if (dbtm >= 0 && dbtm <= 60) {
			this.mind = dbtm;
		} else {
			this.mind = -1;
			throw new CreneauHoraireException(dbtm);
		}
	
		if (finh >= 0 && finh <= 24) {
			this.heuref = finh;
		} else {
			this.heuref = -1;
			throw new CreneauHoraireException(finh);
		}
		
		if (finm >= 0 && finm <= 60) {
			this.minf = finm;
		} else {
			this.minf = -1;
			throw new CreneauHoraireException(finm);
		}
	}
	
	/**
	 * 
	 * @param f
	 */
	public abstract void serializable (File f);
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	public abstract Creneau deserializable (File f);
	
	/**
	 * 
	 */
	public abstract void fragHtmlCons ();
	
	/**
	 * 
	 * @param f
	 * @param bw
	 */
	public abstract void fileHtmlFrag (File f, BufferedWriter bw);	

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + heured;
		result = prime * result + heuref;
		result = prime * result + mind;
		result = prime * result + minf;
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Creneau other = (Creneau) obj;
		if (heured != other.heured)
			return false;
		if (heuref != other.heuref)
			return false;
		if (mind != other.mind)
			return false;
		if (minf != other.minf)
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Creneau [heured=" + heured + ", heuref=" + heuref + ", mind=" + mind + ", minf=" + minf + "]";
	}

	public int getMind() {
		return mind;
	}

	/**
	 * 
	 * @param mind
	 * @throws CreneauHoraireException
	 */
	public void setMind(int mind) throws CreneauHoraireException {
		if (0 <= minf && minf <= 60) {
			this.mind = mind;
		} else throw new CreneauHoraireException(mind);
	}

	/**
	 * 
	 * @return
	 */
	public int getMinf() {
		return minf;
	}

	/**
	 * 
	 * @param minf
	 * @throws CreneauHoraireException
	 */
	public void setMinf(int minf) throws CreneauHoraireException {
		if (0 <= minf && minf <= 60) {
			this.minf = minf;
		} else throw new CreneauHoraireException(minf);
		
	}

	/**
	 * 
	 * @return
	 */
	public int getHeured() {
		return heured;
	}

	/**
	 * 
	 * @param dbt
	 * @throws CreneauHoraireException
	 */
	public void setHeured(int dbt) throws CreneauHoraireException {
		if (dbt >= 0 && dbt <= 24) {
			this.heured = dbt;
		} else {
			throw new CreneauHoraireException(dbt);
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getHeuref() {
		return heuref;
	}

	/**
	 * 
	 * @param fin
	 * @throws CreneauHoraireException
	 */
	public void setHeuref(int fin)	throws CreneauHoraireException {
		if (fin >= 0 && fin <= 24) {
			this.heuref = fin;
		} else {
			throw new CreneauHoraireException(fin);
		}
	}
}
