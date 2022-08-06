package com.ooooo.action;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.ui.jcef.JBCefBrowser;

import javax.swing.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author <a href="https://github.com/ooooo-youwillsee">ooooo</a>
 * @since 1.0.0
 */
public class WebBrowserImpl implements WebBrowser {

  private static final Logger LOG = Logger.getInstance(WebBrowserImpl.class);

  private String curUrl;
  private WebBrowserJPanel browserPanel;

  private JBCefBrowser browser;

  private Deque<String> forwardStack = new LinkedList<>();
  private Deque<String> backStack = new LinkedList<>();

  @Override
  public JComponent getComponent() {
    if (browserPanel == null) {
      browser = new JBCefBrowser();
      browserPanel = new WebBrowserJPanel(browser, this);
    }
    return browserPanel;
  }

  @Override
  public void open(String url) {
    loadUrl(url);
    LOG.info("open url: " + url);

    if (curUrl != null) {
      forwardStack.push(curUrl);
    }
    curUrl = url;
    backStack.clear();
  }

  @Override
  public void forward() {
    if (forwardStack.isEmpty()) {
      return;
    }

    String url = forwardStack.pop();
    LOG.info("forward url: " + url);
    loadUrl(url);

    backStack.push(curUrl);
    curUrl = url;
  }

  @Override
  public void back() {
    if (backStack.isEmpty()) {
      return;
    }

    String url = backStack.pop();
    LOG.info("back url: " + url);
    loadUrl(url);

    forwardStack.push(curUrl);
    curUrl = url;
  }


  private void loadUrl(String url) {
    browser.loadURL(url);
    browserPanel.setUrl(url);
  }

}
