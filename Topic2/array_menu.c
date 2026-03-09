#include <stdio.h>

void inputArray(int a[], int *n)
{
    printf("Enter number of elements: ");
    scanf("%d", n);

    for(int i = 0; i < *n; i++)
    {
        printf("a[%d] = ", i);
        scanf("%d", &a[i]);
    }
}

void outputArray(int a[], int n)
{
    if(n == 0)
    {
        printf("Array is empty!\n");
        return;
    }

    printf("Array elements: ");

    for(int i = 0; i < n; i++)
    {
        printf("%d ", a[i]);
    }

    printf("\n");
}

void printDescending(int a[], int n)
{
    if(n == 0)
    {
        printf("Array is empty!\n");
        return;
    }

    int temp;

    for(int i = 0; i < n-1; i++)
    {
        for(int j = i+1; j < n; j++)
        {
            if(a[i] < a[j])
            {
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
    }

    printf("Array in descending order: ");

    for(int i = 0; i < n; i++)
    {
        printf("%d ", a[i]);
    }

    printf("\n");
}

void checkAllOdd(int a[], int n)
{
    if(n == 0)
    {
        printf("Array is empty!\n");
        return;
    }

    int allOdd = 1;

    for(int i = 0; i < n; i++)
    {
        if(a[i] % 2 == 0)
        {
            printf("%d is even\n", a[i]);
            allOdd = 0;
        }
        else
        {
            printf("%d is odd\n", a[i]);
        }
    }

    if(allOdd)
        printf("All elements are odd numbers.\n");
    else
        printf("Not all elements are odd numbers.\n");
}

void searchValue(int a[], int n)
{
    if(n == 0)
    {
        printf("Array is empty!\n");
        return;
    }

    int x;
    int count = 0;

    printf("Enter value to search: ");
    scanf("%d", &x);

    for(int i = 0; i < n; i++)
    {
        if(a[i] == x)
        {
            count++;
        }
    }

    if(count > 0)
        printf("Value %d appears %d times in the array.\n", x, count);
    else
        printf("Value %d not found in the array.\n", x);
}

int isPrime(int num)
{
    if(num < 2)
        return 0;

    for(int i = 2; i <= num/2; i++)
    {
        if(num % i == 0)
            return 0;
    }

    return 1;
}

void displayPrimeNumbers(int a[], int n)
{
    if(n == 0)
    {
        printf("Array is empty!\n");
        return;
    }

    printf("Prime numbers in the array: ");

    for(int i = 0; i < n; i++)
    {
        if(isPrime(a[i]))
        {
            printf("%d ", a[i]);
        }
    }

    printf("\n");
}

int main()
{
    int choice;
    int a[100];
    int n = 0;

    while(1)
    {
        printf("\n===== ARRAY MANAGEMENT MENU =====\n");
        printf("1. Input the array\n");
        printf("2. Output the array\n");
        printf("3. Print array in descending order\n");
        printf("4. Check if all elements are odd\n");
        printf("5. Search a value\n");
        printf("6. Display prime numbers\n");
        printf("7. Quit\n");

        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch(choice)
        {
            case 1:
                inputArray(a, &n);
                break;

            case 2:
                outputArray(a, n);
                break;

            case 3:
                printDescending(a, n);
                break;

            case 4:
                checkAllOdd(a, n);
                break;

            case 5:
                searchValue(a, n);
                break;

            case 6:
                displayPrimeNumbers(a, n);
                break;

            case 7:
                printf("Exit selected\n");
                return 0;

            default:
                printf("Invalid choice\n");
        }
    }

    return 0;
}