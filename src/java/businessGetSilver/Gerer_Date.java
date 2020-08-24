package businessGetSilver;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * <p>Titre : </p>
 * <p>Description : </p>
 * <p>Copyright : Copyright (c) 2005</p>
 * <p>Soci?t? : </p>
 * @author non attribuable
 * @version 1.0
 */

public class Gerer_Date {
  public Gerer_Date() {
  }

  public String getJour() {
    Calendar c = Calendar.getInstance();
    int jour = c.get(Calendar.DAY_OF_MONTH);
    int mois = c.get(Calendar.MONTH) + 1;
    int annee = c.get(Calendar.YEAR);
    String str_jour, str_mois, str_annee;
    if (jour < 10) {
      str_jour = "0" + jour;
    }
    else {
      str_jour = String.valueOf(jour);
    }
    if (mois < 10) {
      str_mois = "0" + mois;
    }
    else {
      str_mois = String.valueOf(mois);
    }
    String strDate = str_jour + "-" + str_mois + "-" + annee;
    return strDate;
  }

  public String getJour2() {
    Calendar c = Calendar.getInstance();
    int jour = c.get(Calendar.DAY_OF_MONTH);
    int mois = c.get(Calendar.MONTH) + 1;
    int annee = c.get(Calendar.YEAR);
    String str_jour, str_mois, str_annee;
    if (jour < 10) {
      str_jour = "0" + jour;
    }
    else {
      str_jour = String.valueOf(jour);
    }
    if (mois < 10) {
      str_mois = "0" + mois;
    }
    else {
      str_mois = String.valueOf(mois);
    }
    String strDate =  str_jour + "-" + str_mois + "-" +annee;
    return strDate;
  }

  public String Get7JourApres(String str_date) {
    String result = str_date;
    for (int i = 0; i < 6; i++) {
      result = GetJourSuivant(result);
    }
    return result;
  }

  public String GetJourSuivant(String str_date) {
    String result = "";
    Calendar c = Calendar.getInstance();
    String str_jour, str_mois;
    StringTokenizer st = new StringTokenizer(str_date, "/");

    int jour = Integer.parseInt(st.nextToken());
    int mois = Integer.parseInt(st.nextToken());
    int year = Integer.parseInt(st.nextToken());

    int LimiteFevrier, AnneeVol;
    AnneeVol = c.get(Calendar.YEAR);

    if (mois == 1 || mois == 3 || mois == 5 || mois == 7 ||
        mois == 8 || mois == 10 || mois == 12) {

      if (jour < 31) {
        jour++;
      }
      else {
        jour = 1;
        if (mois < 12) {
          mois++;
        }
        else {
          mois = 1;
          AnneeVol++;
        }
      }
    }

    else if (mois == 4 || mois == 6 || mois == 9 || mois == 11) {
      if (jour < 30) {
        jour++;
      }
      else {
        jour = 1;
        mois++;
      }
    }

    else {
      if ( ( (year % 4 == 0) && ! (year % 100 == 0))
          || (year % 400 == 0)) {
        LimiteFevrier = 29;
      }
      else {
        LimiteFevrier = 28;
      }

      if (jour < LimiteFevrier) {
        jour++;
      }
      else {
        jour = 1;
        mois++;
      }
    }

    if (jour < 10) {
      str_jour = "0" + jour;
    }
    else {
      str_jour = String.valueOf(jour);
    }
    if (mois < 10) {
      str_mois = "0" + mois;
    }
    else {
      str_mois = String.valueOf(mois);
    }

    result = str_jour + "-" + str_mois + "-" + AnneeVol;
    return result;
  }

  public String Get_Heure() {
    String heure = "";
    Calendar c = Calendar.getInstance();

    int hr = c.get(Calendar.HOUR_OF_DAY);
    int min = c.get(Calendar.MINUTE);

    String h = "";
    String m = "";

    if (hr < 10) {
      h = "0" + hr;
    }
    else {
      h = new Integer(hr).toString();
    }
    if (min < 10) {
      m = "0" + min;
    }
    else {
      m = new Integer(min).toString();
    }

    heure = h + ":" + m;
    return heure;
  }

  public String Get_HeurePrecedente() {
    String heure = "";
    Calendar c = Calendar.getInstance();

    int hr = c.get(Calendar.HOUR_OF_DAY) - 1;
    int min = c.get(Calendar.MINUTE);

    if (hr < 0) {
      hr = 23;
    }
    String h = "";
    String m = "";

    if (hr < 10) {
      h = "0" + hr;
    }
    else {
      h = new Integer(hr).toString();
    }
    if (min < 10) {
      m = "0" + min;
    }
    else {
      m = new Integer(min).toString();
    }

    heure = h + ":" + m;
    return heure;
  }

  public static void main(String[] args) {
    Gerer_Date gerer = new Gerer_Date();
    gerer.getJour2();
  }

}
