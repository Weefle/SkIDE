package com.skide.gui.controllers

import com.skide.include.Addon
import com.skide.include.CompileOption
import com.skide.include.CompileOptionType
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.ComboBox
import javafx.scene.control.CheckBox
import java.io.File


class ProjectSettingsGUIController {

    @FXML
    lateinit var prNameTextField: TextField

    @FXML
    lateinit var skriptVersionComboBox: ComboBox<*>

    @FXML
    lateinit var plListView: ListView<Addon>

    @FXML
    lateinit var plUpdateBtn: Button

    @FXML
    lateinit var plNameLabel: Label

    @FXML
    lateinit var plDescriptionLabel: Label

    @FXML
    lateinit var plAuthorLabel: Label

    @FXML
    lateinit var plVersionsComboBox: ComboBox<String>

    @FXML
    lateinit var enableSupportCheckBox: CheckBox

    @FXML
    lateinit var compileOutPutPathField: TextField

    @FXML
    lateinit var compileConfListView: ListView<CompileOption>

    @FXML
    lateinit var compileDelConfBtn: Button

    @FXML
    lateinit var compileNewConfBtn: Button

    @FXML
    lateinit var compileMethodComboBox: ComboBox<CompileOptionType>

    @FXML
    lateinit var removeCommentsCheck: CheckBox

    @FXML
    lateinit var removeEmptyLinesCheck: CheckBox

    @FXML
    lateinit var compileObfuscateCheck: CheckBox

    @FXML
    lateinit var compileOutPutBtn: Button

    @FXML
    lateinit var obsfsucateLevelComboBox: ComboBox<*>

    @FXML
    lateinit var compileIncludedFileList: ListView<File>

    @FXML
    lateinit var compileExcludedFileList: ListView<File>

    @FXML
    lateinit var applyBtn: Button

    @FXML
    lateinit var okBtn: Button

    @FXML
    lateinit var cancelBtn: Button

    @FXML
    lateinit var compileNewOptionsNameField: TextField

}