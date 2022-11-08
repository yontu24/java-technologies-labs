package ro.uaic.info.app.converters;

import ro.uaic.info.app.dao.TeamRepository;
import ro.uaic.info.app.model.Team;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("teamConverter")
//@Named
public class TeamConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        ValueExpression valueExpression = facesContext
                .getApplication()
                .getExpressionFactory()
                .createValueExpression(facesContext.getELContext(), "#{teamRepository}", TeamRepository.class);

        TeamRepository teamRepository = (TeamRepository) valueExpression.getValue(facesContext.getELContext());
        return teamRepository.getTeam(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Team) {
            Team t = (Team) o;
            return t.getName();
        }
        return null;
    }
}
