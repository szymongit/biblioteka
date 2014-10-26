package pl.altkom.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LanguageBean {

    private String language;

    public String switchLocale() {
        if ("pl".equals(language)) {
            language = Locale.EN.name().toLowerCase();
        } else {
            language = Locale.PL.name().toLowerCase();
        }
        return "logout";
    }

    public boolean isPolish() {
        return language.equalsIgnoreCase(Locale.PL.name());
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LanguageBean() {
        language = Locale.PL.name().toLowerCase();
    }

    enum Locale {

        PL, EN;
    }
}
