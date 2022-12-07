package day7

fun solveA(input: List<String>) = input.createFolderStructure().filter { it.getSize() < 100000L }.sumOf { it.getSize() }

fun solveB(input: List<String>): Long {
    val folders = input.createFolderStructure()

    val rootFolderSize = folders.find { it.name == "/" }!!.getSize()

    return folders.findSmallestToDelete(totalUsedSpace = rootFolderSize, neededFreeSpace = 40000000L)
}

fun List<String>.createFolderStructure(): List<Folder> {
    var currentFolder: Folder? = null
    val folders = mutableListOf<Folder>()

    this.map { it.split(" ") }.forEach { command ->
        if (command.isChangeDirIn()) {
            currentFolder = createNewFolder(name = command[2], parentFolder = currentFolder, folders)
        }

        if (command.isChangeDirOut()) currentFolder = currentFolder?.parentFolder

        if (command.isFile()) currentFolder!!.files.add(File(command[1], command[0].toLong()))
    }

    return folders
}

fun List<String>.isFile() = this.first().toLongOrNull() != null

fun List<String>.isChangeDirIn() = this[1] == "cd" && this[2] != ".."

fun List<String>.isChangeDirOut() = this[1] == "cd" && this[2] == ".."

fun createNewFolder(name: String, parentFolder: Folder?, folders: MutableList<Folder>): Folder {
    val newFolder = Folder(name, parentFolder, mutableListOf(), mutableListOf())
    folders.add(newFolder)
    parentFolder?.subFolder?.add(newFolder)
    return newFolder
}

fun List<Folder>.findSmallestToDelete(totalUsedSpace: Long, neededFreeSpace: Long) = this.sortedBy { it.getSize() }
    .first { totalUsedSpace - it.getSize() < neededFreeSpace }
    .getSize()


data class File(val name: String, val size: Long)

data class Folder(
    val name: String,
    val parentFolder: Folder?,
    val files: MutableList<File>,
    val subFolder: MutableList<Folder>
) {
    fun getSize(): Long = files.sumOf { it.size } + subFolder.sumOf { it.getSize() }
}

// first approach
////25:50
//fun solveA(input: List<String>): Long {
//
//    val folders = mutableListOf<Folder>()
//    var currentFolder: Folder? = null
//
//    for (line in input) {
//        println("CURRENT ${currentFolder?.name}")
//        val command = line.split(" ")
//        if (command[0] == "$" && command[1] == "cd") {
//            currentFolder = if (command[2] != "..") {
//                val newFolder = Folder(command[2], currentFolder, mutableListOf(), mutableListOf())
//                println("ADDING NEW FOLDER: ${newFolder.name} to ${currentFolder?.name}")
//                folders.add(newFolder)
//                currentFolder?.subFolder?.add(newFolder)
//                newFolder
//            } else {
//                currentFolder?.parentFolder
//            }
//        } else if (command[0].toLongOrNull() != null) {
//            val newFile = File(command[1], command[0].toLong())
//            currentFolder?.files?.add(newFile)
//        }
//    }
//
//    println(folders.filter { it.getSize() < 100000L })
//
//    return folders.filter { it.getSize() < 100000L }.sumOf { it.getSize() }
//}
//
////7:03
//fun solveB(input: List<String>): Long {
//    val folders = mutableListOf<Folder>()
//    var currentFolder: Folder? = null
//
//    for (line in input) {
//        println("CURRENT ${currentFolder?.name}")
//        val command = line.split(" ")
//        if (command[0] == "$" && command[1] == "cd") {
//            currentFolder = if (command[2] != "..") {
//                val newFolder = Folder(command[2], currentFolder, mutableListOf(), mutableListOf())
//                println("ADDING NEW FOLDER: ${newFolder.name} to ${currentFolder?.name}")
//                folders.add(newFolder)
//                currentFolder?.subFolder?.add(newFolder)
//                newFolder
//            } else {
//                currentFolder?.parentFolder
//            }
//        } else if (command[0].toLongOrNull() != null) {
//            val newFile = File(command[1], command[0].toLong())
//            currentFolder?.files?.add(newFile)
//        }
//    }
//
//    val totalDiskSpace = 70000000L
//    val totalUsed = folders.find { it.name == "/" }!!.getSize()
//    val available = totalDiskSpace - totalUsed
//
//    println("Available $available")
//
//    val smallestToDelete = folders.filter { it.name != "/" }
//        .sortedBy { it.getSize() }
//        .first { totalUsed - it.getSize() < 40000000L }
//
//    return smallestToDelete.getSize()
//}
