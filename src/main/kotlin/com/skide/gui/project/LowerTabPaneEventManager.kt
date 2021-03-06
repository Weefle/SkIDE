package com.skide.gui.project

import com.skide.CoreManager
import com.skide.core.management.RunningServerManager
import com.skide.gui.DragResizer
import com.skide.gui.controllers.ProjectGUIController
import com.skide.include.Server
import com.skide.utils.setIcon
import com.terminalfx.TerminalBuilder
import javafx.application.Platform
import javafx.scene.control.Button
import javafx.scene.control.Tab
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox

class LowerTabPaneEventManager(val ctrl: ProjectGUIController, val openProjectGuiManager: OpenProjectGuiManager, val coreManager: CoreManager) {

    var visible = true
    val rootPane = ctrl.mainLowerBorderPane
    val tabPane = ctrl.lowerTabPane
    val terminalBuilder = TerminalBuilder()
    val buildLogArea = TextArea()
    private val running = HashMap<Server, Tab>()


    fun getServerTab(serverManager: RunningServerManager): Triple<Tab, Button, Button> {

        if (tabPane.tabs[1]!!.isDisable) tabPane.tabs[1]!!.isDisable = false
        if (running.containsKey(serverManager.server)) {
            ctrl.runsTabPane.tabs.remove(running[serverManager.server]!!)
            running.remove(serverManager.server)
        }
        val tab = Tab(serverManager.server.configuration.name)

        val pane = BorderPane()
        val vBox = VBox()
        val reloadBtn = Button("R")
        reloadBtn.setIcon("refresh", false)
        val stopBtn = Button("S")
        stopBtn.setIcon("stop", false)
        val cleanBtn = Button("C")
        cleanBtn.setIcon("delete", false)
        cleanBtn.setOnAction {
            serverManager.cleanFiles()
        }


        vBox.children.addAll(reloadBtn, stopBtn, cleanBtn)
        pane.left = vBox
        val sendField = TextField()
        sendField.setOnKeyPressed { ev ->
            if (ev.code == KeyCode.ENTER) {
                serverManager.sendCommand(sendField.text)
                sendField.text = ""
            }
        }
        pane.bottom = sendField
        pane.center = serverManager.area

        tab.content = pane
        ctrl.runsTabPane.tabs.add(tab)
        ctrl.runsTabPane.selectionModel.select(tab)
        tabPane.selectionModel.select(1)
        running[serverManager.server] = tab
        return Triple(tab, reloadBtn, stopBtn)
    }

    fun setupBuildLogTabForInput(): (String) -> Unit {

        buildLogArea.clear()
        if (tabPane.tabs[2]!!.isDisable) tabPane.tabs[2]!!.isDisable = false
        tabPane.selectionModel.select(2)

        val recaller = { x: String ->

            Platform.runLater {
                buildLogArea.appendText("$x\n")
            }
        }

        return recaller
    }

    fun setup() {

        val buildLogTab = Tab("Build Log")
        buildLogTab.isDisable = true
        buildLogTab.content = buildLogArea
        tabPane.tabs.add(buildLogTab)
        buildLogArea.isEditable = false

        DragResizer().makeResizable(ctrl.mainLowerBorderPane)
        terminalBuilder.terminalPath = openProjectGuiManager.openProject.project.folder.toPath()
        if (coreManager.configManager.get("theme") == "Dark") {
            terminalBuilder.terminalConfig.backgroundColor = "#2B2B2B"
            terminalBuilder.terminalConfig.foregroundColor = "#dbe0dc"
        }

        ctrl.lowerTabPaneToggleBtn.setIcon("download")
        ctrl.lowerTabPaneToggleBtn.setOnAction {
            if (visible) {
                ctrl.lowerTabPaneToggleBtn.setIcon("upload")

                rootPane.prefHeight = 0.0
            } else {
                ctrl.lowerTabPaneToggleBtn.setIcon("download")
                rootPane.prefHeight = 200.0
            }
            visible = !visible
        }
        ctrl.consoleAddBtn.setIcon("plus", false)

        ctrl.consoleAddBtn.setOnAction {

            val tab = terminalBuilder.newTerminal()
            tab.text = "Console"
            tab.isClosable = false
            ctrl.consoleTabArea.tabs.add(tab)


            ctrl.consoleTabArea.selectionModel.select(tab)
        }
        ctrl.consoleRemBtn.setIcon("delete", false)
        ctrl.consoleRemBtn.setOnAction {
            if (ctrl.consoleTabArea.tabs.size == 1) {
                ctrl.consoleTabArea.tabs.clear()
                val tab = terminalBuilder.newTerminal()
                tab.text = "Console"
                tab.isClosable = false
                ctrl.consoleTabArea.tabs.add(tab)
                ctrl.consoleTabArea.selectionModel.select(tab)
            } else {
                val curr = ctrl.consoleTabArea.selectionModel.selectedItem

                ctrl.consoleTabArea.tabs.remove(curr)
            }
        }

        val tab = terminalBuilder.newTerminal()
        tab.text = "Console"
        tab.isClosable = false
        ctrl.consoleTabArea.tabs.add(tab)


        ctrl.consoleTabArea.selectionModel.select(tab)

        tabPane.tabs[1]!!.isDisable = true
    }
}