/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.as.console.client.widgets.forms;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.as.console.client.widgets.ComboBox;
import org.jboss.as.console.client.widgets.icons.Icons;

import java.util.List;

/**
 * @author Heiko Braun
 * @date 2/21/11
 */
public class ComboBoxItem extends FormItem<String> {


    private ComboBox comboBox;
    private boolean defaultToFirst;

    private InputElementWrapper wrapper;

    public ComboBoxItem(String name, String title) {
        super(name, title);
        this.comboBox = new ComboBox();
        this.wrapper = new InputElementWrapper(comboBox.asWidget(), this);
        wrapper.getElement().setAttribute("style", "width:100%");

    }

    @Override
    public String getValue() {
        return comboBox.getSelectedValue();
    }

    @Override
    public void setValue(String value) {

        comboBox.clearSelection();

        for(int i=0; i< comboBox.getItemCount(); i++)
        {
            if(comboBox.getValue(i).equals(value))
            {
                comboBox.setItemSelected(i, true);
                break;
            }
        }
    }

    @Override
    public Widget asWidget() {
        return wrapper;
    }

    public void setDefaultToFirstOption(boolean b) {
        this.defaultToFirst = b;
    }

    public void setValueMap(String[] values) {
        comboBox.clearValues();
        for(String s : values)
        {
            comboBox.addItem(s);
        }

        if(defaultToFirst)
            comboBox.setItemSelected(0, true);
    }

    public void setValueMap(List<String> values) {
        comboBox.clearValues();
        for(String s : values)
        {
            comboBox.addItem(s);
        }

        if(defaultToFirst)
            comboBox.setItemSelected(0, true);
    }

    @Override
    public void setEnabled(boolean b) {
        comboBox.setEnabled(b);
    }

    @Override
    public boolean validate(String value) {
        if(isRequired() && comboBox.getSelectedValue().equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void setErroneous(boolean b) {
        super.setErroneous(b);
        wrapper.setErroneous(b);
    }
}
