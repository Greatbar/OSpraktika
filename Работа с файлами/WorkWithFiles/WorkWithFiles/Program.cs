using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace WorkWithFiles
{
    class Program
    {
        static void Main(string[] args)
        {
            bool go = true;
            while (go)
            {
                Menu();
                int chose = Convert.ToInt32(Console.ReadLine());
                switch (chose)
                {
                    case 1:
                        DrivesInformation.Zadanie_1();
                        break;
                    case 2:
                        Tools.TXT();
                        break;
                    case 3:
                        Tools.Json();
                        break;
                    case 4:
                        Tools.XML();
                        break;
                    case 5:
                        Tools.ZIP();
                        break;
                    case 6:
                        go = false;
                        break;
                }
            }
        }
        private static void Menu()
        {
            Console.WriteLine("\n1 - Задание 1 (Инф. о дисках)");
            Console.WriteLine("2 - Задание 2 (Работа TXT)");
            Console.WriteLine("3 - Задание 3 (Работа JSON)");
            Console.WriteLine("4 - Задание 4 (Работа XML)");
            Console.WriteLine("5 - Задание 5 (Работа ZIP)");
            Console.WriteLine("6 - Выход\n");
            Console.WriteLine("Выберите что вы хотите сделать: ");
        }
    }
}
