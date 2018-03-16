def mimeFile = new File("mime.types")

mimeFile.eachLine { line ->
    if (!line.startsWith('#')) {
        def parts = line.split('\\s+')
        if (parts.length > 1) {
            def mimeType = parts[0]
            for (int i = 1; i < parts.length; ++i)
                println("MIME_TYPE_MAP.put(\"${parts[i]}\", \"$mimeType\");")
        }
    }
}
