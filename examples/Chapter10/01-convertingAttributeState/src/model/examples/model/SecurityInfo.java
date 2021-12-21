package examples.model;

import java.text.DateFormat;
import java.util.Date;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class SecurityInfo {
	
    private boolean bonded;

    @Temporal(TemporalType.DATE)
    private Date dateBonded;

    public boolean getBonded() {
        return bonded;
    }

    public void setBonded(boolean bonded) {
        this.bonded = bonded;
    }

    public Date getDateBonded() {
        return dateBonded;
    }

    public void setDateBonded(Date dateBonded) {
        this.dateBonded = dateBonded;
    }
    
    public String toString() {
        return "SecInfo(" + 
               "bonded: " + getBonded() +
               (dateBonded == null ? ")" :
                   (", date: " + DateFormat.getDateInstance().format(dateBonded) + ")"));
    }
}
