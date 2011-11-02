package org.jboss.as.console.client.domain.runtime;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.ballroom.client.widgets.forms.ComboBox;

import java.util.List;

/**
 * @author Heiko Braun
 * @date 11/2/11
 */
public class HostSelector {

    private ComboBox hosts;
    private ComboBox servers;
    private boolean serverSelection = true;

    public Widget asWidget() {

        HorizontalPanel layout = new HorizontalPanel();
        layout.getElement().setAttribute("style","padding:4px;");
        hosts = new ComboBox();
        servers = new ComboBox();

        Label hostLabel = new Label("Host:");
        hostLabel.setStyleName("header-label");
        layout.add(hostLabel);
        Widget hWidget = hosts.asWidget();
        hWidget.getElement().setAttribute("style", "width:100px;");
        layout.add(hWidget);

        if(serverSelection)
        {
            Label serverLabel = new Label("Server:");
            serverLabel.setStyleName("header-label");
            serverLabel.getElement().setAttribute("style", "padding-top:2px; padding-left:10px;");
            layout.add(serverLabel);
            Widget sWidget = servers.asWidget();
            sWidget.getElement().setAttribute("style", "width:100px;");
            layout.add(sWidget);
        }

        return layout;
    }

    public void setServerSelection(boolean serverSelection) {
        this.serverSelection = serverSelection;
    }

    public void setHosts(List<String> hostNames)
    {
        hosts.setValues(hostNames);
    }

    public void setServersOnHost(String host, List<String> serverNames)
    {
        servers.setValues(serverNames);

        for(int i=0; i<hosts.getItemCount();i++)
        {
            if(host.equals(hosts.getValue(i)))
                hosts.setItemSelected(i, true);
            else
                hosts.setItemSelected(i, false);
        }
    }
}