using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;
using System.IO.Compression;

namespace WorkWithFiles
{
    class Tools
    {
        class Person
        {
            public string Name { get; set; }
            public int Age { get; set; }
        }

        public static void TXT()
        {
            string path = @"G:\дз\Операционные системы\Работа с файлами\Test.txt";
            if (!File.Exists(path))
            {
                File.Create(path).Close();
            }
            Console.WriteLine("Файл успешно создан!");
            Console.WriteLine("Введите строку для записи в файл:");
            string text = Console.ReadLine();
            using (FileStream fstream = new FileStream($"{path}", FileMode.OpenOrCreate))
            {
                byte[] array = System.Text.Encoding.Default.GetBytes(text);
                // записываем данные массива байтов в файл
                fstream.Write(array, 0, array.Length);
                Console.WriteLine("Текст записан в файл!");
            }
            using (FileStream fstream = File.OpenRead($"{path}"))
            {
                byte[] array = new byte[fstream.Length];
                // считываем данные
                fstream.Read(array, 0, array.Length);
                // декодируем байты в строку
                string textFromFile = System.Text.Encoding.Default.GetString(array);
                Console.WriteLine($"Текст из файла: {textFromFile}");
            }
            Console.WriteLine("Вы хотите удалить файл? 1-удалить, 2-отмена;");
            string chose = Console.ReadLine();
            switch (chose)
            {
                case "1":
                    File.Delete(path);
                    Console.WriteLine("Файл удалён.");
                    break;
                case "2":
                    Console.WriteLine("Файл не был удалён.");
                    break;

            }
        }

        public static void Json()
        {
            string file = @"G:\дз\Операционные системы\Работа с файлами\.json";

            Person Seva = new Person { Name = "SevaT", Age = 20 };
            string json = JsonSerializer.Serialize<Person>(Seva);

            using (FileStream fst = File.Create(file))
            {
                byte[] info = new UTF8Encoding(true).GetBytes(json);
                fst.Write(info, 0, info.Length);
                Console.WriteLine("Файл Json успешно создан!");
            }

            using (StreamReader str = File.OpenText(file))
            {
                string s;
                Console.Write("Текст из файла: ");

                while ((s = str.ReadLine()) != null)
                {
                    Console.WriteLine(s);
                }
            }

            Person restoredPerson = JsonSerializer.Deserialize<Person>(json);
            Console.WriteLine($"Десериализованный текст: Name: {restoredPerson.Name}  Age: {restoredPerson.Age}");
            Console.WriteLine("Вы хотите удалить файл? 1-удалить, 2-отмена;");

            string chose = Console.ReadLine();
            switch (chose)
            {
                case "1":
                    File.Delete(file);
                    Console.WriteLine("Файл удалён.");
                    break;
                case "2":
                    Console.WriteLine("Файл не был удалён.");
                    break;
            }
        }

        public static void XML()
        {
            string file = @"G:\дз\Операционные системы\Работа с файлами\Staff.xml";

            XDocument xdoc = new XDocument(new XElement("Staff",
                new XElement("Boys",
                    new XAttribute("name", "Seva"),
                    new XElement("Age", "20")),
                new XElement("Girls",
                    new XAttribute("name", "Lera"),
                    new XElement("Age", "18"))));
            xdoc.Save(file);
            Console.WriteLine("Файл XML успешно создан и сохранен!");
            Console.WriteLine("Чтение текста из файла:");
            Console.WriteLine(xdoc);
            Console.WriteLine("Вы хотите удалить файл? 1-удалить, 2-отмена;");
            string chose = Console.ReadLine();
            switch (chose)
            {
                case "1":
                    File.Delete(file);
                    Console.WriteLine("Файл удалён.");
                    break;
                case "2":
                    Console.WriteLine("Файл не был удалён.");
                    break;
            }
        }

        public static void ZIP()
        {
            string sourceFile = @"G:\дз\Операционные системы\Работа с файлами\Для сжатия.txt"; // исходный файл
            string compressedFile = @"G:\дз\Операционные системы\Работа с файлами\Для сжатия.gz";
            string targetFile = @"G:\дз\Операционные системы\Работа с файлами\Для сжатия_новый.txt"; // восстановленный файл
            using (FileStream sourceStream = new FileStream(sourceFile, FileMode.OpenOrCreate))
            {
                // поток для записи сжатого файла
                using (FileStream targetStream = File.Create(compressedFile))
                {
                    // поток архивации
                    using (GZipStream compressionStream = new GZipStream(targetStream, CompressionMode.Compress))
                    {
                        sourceStream.CopyTo(compressionStream); // копируем байты из одного потока в другой
                        Console.WriteLine("Сжатие файла {0} завершено.", sourceFile);
                        Console.WriteLine("Исходный размер: {0}  сжатый размер: {1}.",
                            sourceStream.Length.ToString(), targetStream.Length.ToString());
                    }
                }
            }
            // поток для чтения из сжатого файла
            using (FileStream sourceStream = new FileStream(compressedFile, FileMode.OpenOrCreate))
            {
                // поток для записи восстановленного файла
                using (FileStream targetStream = File.Create(targetFile))
                {
                    // поток разархивации
                    using (GZipStream decompressionStream = new GZipStream(sourceStream, CompressionMode.Decompress))
                    {
                        decompressionStream.CopyTo(targetStream);
                        Console.WriteLine("Восстановленый файл: {0}", targetFile);
                    }
                }
            }
        }
    }
}