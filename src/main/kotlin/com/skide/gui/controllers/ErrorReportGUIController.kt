package com.skide.gui.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.control.CheckBox


class ErrorReportGUIController {

    @FXML
    lateinit var discordLink: Button

    @FXML
    lateinit var githubLink: Button

    @FXML
    lateinit var contentArea: TextArea

    @FXML
    lateinit var copyToClipBoard: Button

    @FXML
    lateinit var detailsCheck: CheckBox
}