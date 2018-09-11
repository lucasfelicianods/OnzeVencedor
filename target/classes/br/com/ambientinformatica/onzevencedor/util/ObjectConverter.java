package br.com.ambientinformatica.onzevencedor.util;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ObjectConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		Object resp = null;
		if (value != null) {
			resp = getAttributesFrom(component).get(value);
		}
		if (resp instanceof String) {
			resp = null;
		}
		return resp;
	}

	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		String key;
		Object valor;
		if (value == null) {
			key = String.valueOf("".hashCode());
			valor = "";
		} else {
			key = String.valueOf(value.hashCode());
			valor = value;
		}
		getAttributesFrom(component).put(key, valor);
		return key;
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
