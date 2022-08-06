package com.ooooo.action;

import javax.swing.*;

/**
 * @author <a href="https://github.com/ooooo-youwillsee">ooooo</a>
 * @since 1.0.0
 */
public interface WebBrowser {

  String DEFAULT_URL = "http://www.google.com";

  JComponent getComponent();

  void open(String url);

  void back();

  void forward();
}
