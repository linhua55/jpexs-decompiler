/*
 * Copyright (C) 2014 JPEXS
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.jpexs.decompiler.flash.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author JPEXS
 * @param <E>
 */
public class SearchResultsDialog<E> extends AppDialog implements ActionListener {

    private final JList<E> resultsList;
    private final DefaultListModel<E> model;
    private final SearchListener<E> listener;

    private static final String ACTION_GOTO = "GOTO";
    private static final String ACTION_CANCEL = "CLOSE";

    private final JButton gotoButton = new JButton(translate("button.goto"));
    private final JButton closeButton = new JButton(translate("button.close"));

    public SearchResultsDialog(Window owner, String text, SearchListener<E> listener) {
        super(owner);
        setTitle(translate("dialog.title").replace("%text%", text));
        Container cnt = getContentPane();
        model = new DefaultListModel<>();
        resultsList = new JList<>(model);
        this.listener = listener;

        gotoButton.setActionCommand(ACTION_GOTO);
        gotoButton.addActionListener(this);
        closeButton.setActionCommand(ACTION_CANCEL);
        closeButton.addActionListener(this);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(gotoButton);
        buttonsPanel.add(closeButton);
        resultsList.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    gotoElement();
                }
            }

        });

        cnt.setLayout(new BorderLayout());
        JScrollPane sp = new JScrollPane(resultsList);
        sp.setPreferredSize(new Dimension(300, 300));
        cnt.add(sp, BorderLayout.CENTER);
        cnt.add(buttonsPanel, BorderLayout.SOUTH);
        pack();
        View.centerScreen(this);
        View.setWindowIcon(this);
    }

    public void setResults(List<E> results) {
        model.clear();
        for (E e : results) {
            model.addElement(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ACTION_GOTO:
                gotoElement();
                setVisible(false);
                break;
            case ACTION_CANCEL:
                setVisible(false);
                break;
        }
    }

    private void gotoElement() {
        if (resultsList.getSelectedIndex() != -1) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    listener.updateSearchPos(resultsList.getSelectedValue());
                }

            });
        }
    }

}
