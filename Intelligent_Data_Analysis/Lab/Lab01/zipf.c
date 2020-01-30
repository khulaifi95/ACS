/*******************************************************/
/*                                                     */
/*      C programme to demonstrate Zipf's law          */
/*                                                     */
/*          Written for EE3J2 Data Mining              */
/*                                                     */
/*        Version 1: Martin Russell 17/01/04           */
/*                                                     */
/* Dept. Electronic, Electrical & Computer Engineering */
/*            University of Birmingham                 */
/*                                                     */
/*    To compile under linux:                          */
/*                 gcc zipf.c                          */
/*                 mv a.out zipf                       */
/*                                                     */
/*    To run:                                          */
/*                 zipf textFileName                   */
/*                                                     */
/*******************************************************/

#define MAX_STR_LEN 512

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <ctype.h>

/********************************************************/

/* struture to store linked list of words */
struct item {
  char *text;
  int count;
  struct item *nextItem;
};

/********************************************************/

/* Function to convert upper case letter in a token to lower case */
void upper2lower(char *token)
{
  int c;
  for (c=0; c<strlen(token); c++) token[c]=tolower(token[c]);
}

/********************************************************/

/* Function to remove punctuation from start and end of word */
int removePunct(char *token)
{
  int l;
  int r; /* counts number of characters removed */
  r=0;
  l=strlen(token);
  /* first remove punctuation from the end of the word */
  /* 'isalnum[x]' returns true if x is a letter or number */
  while ((!isalnum(token[l-1])) && (strlen(token)>0))
    {
      token[l-1]='\0';
      l--;
      r++;
    }
  /* now remove punctuation from the start of the word */
  while ((!isalnum(token[0])) && (strlen(token)>0))
    {
      int c;
      l=strlen(token);
      for (c=0; c<l; c++) token[c]=token[c+1];
      token[c]='\0';
      r++;
    }
  return(r);
}

/********************************************************/

/* Function to read next word from text */
int nextWord(FILE *ip, char *token)
{
  int x;
  int c;
  for (c=0; c<MAX_STR_LEN; c++) token[c]='\0';
  x=fscanf(ip,"%s",token);
  token[strlen(token)]='\0';
  if (x != EOF)
    {
      upper2lower(token);
      removePunct(token);
    }
  return x;
}

/********************************************************/

/* Function to check linked list */
int checkList(struct item root)
{
  int i;
  struct item *curItem;

  /* initialise */
  i=1;
  curItem=root.nextItem;
  printf("\nPrinting linked list\n====================\n\nlist size=%d\n\n",root.count);
  while ((curItem->nextItem)!=NULL)
    {
      printf("%d %s %d\n",i,curItem->text,curItem->count);
      curItem=curItem->nextItem;
      i++;
    }
  return i;
}

/********************************************************/

/* Function to print linked list to file*/
int printList(struct item root)
{
  int i;
  struct item *curItem;
  FILE *op;

  /* open output file */
  op=fopen("results","w");
  /* initialise */
  i=1;
  curItem=root.nextItem;
  fprintf(op,"\nPrinting linked list\n====================\n\nlist size=%d\n\n",root.count);
  while ((curItem->nextItem)!=NULL)
    {
      fprintf(op,"%d %s %d\n",i,curItem->text,curItem->count);
      curItem=curItem->nextItem;
      i++;
    }
  return(i);
}

/********************************************************/

/* Function to order the linked list according to word frequency */
int sortList(struct item *root)
{
  int swap;
  int swapCnt;
  struct item *prevIt;
  struct item *curIt;
  struct item *nextIt;

  /* initialise */
  swap=1;
  swapCnt=0;

  /* outer loop - keep going til no more swaps occur */
  while (swap==1)
    {

      /* reset swap flag */
      swap=0;

      /* set pointers to start of list */
      prevIt=root;
      curIt=root->nextItem;
      nextIt=curIt->nextItem;

      while (((curIt->count) >= (nextIt->count)) && (nextIt->nextItem)!=NULL)
	{
	  /* move one entry along the linked list */
	  prevIt=curIt;
	  curIt=nextIt;
	  nextIt=curIt->nextItem;
	}
      if ((nextIt->nextItem)!=NULL)
	{
	  struct item *tmp;
	  /* swap items */
	  swap=1;
	  swapCnt++;

	  /* set temporary pointer */
	  tmp=nextIt->nextItem;
	  /* set pointer from previous item */
	  prevIt->nextItem=nextIt;
	  /* set pointer from nextIt (new curIt) */
	  nextIt->nextItem=curIt;
	  /* set pointer from curIt (new nextIt) */
	  curIt->nextItem=tmp;
	}
    }
  return(swapCnt);
}

/********************************************************/

/* Function to create new item in linked list */
void createNewItem
(struct item *prevIt, struct item *newIt, struct item *nextIt, char *wrd, int *listSize)
{
  int c;
  /* allocate memory for new linked list item */
  newIt=calloc(1,sizeof(struct item));
  /* set pointer to new item */
  prevIt->nextItem=newIt;
  /* set entries in new item */
  newIt->nextItem=nextIt;
  newIt->count=1;
  newIt->text=calloc(strlen(wrd)+1,sizeof(char));
  for (c=0; c<strlen(wrd); c++)
    {
      newIt->text[c]=wrd[c];
    }
  newIt->text[c]='\0';
  /* increment listSize */
  (*listSize)++;
}

/********************************************************/

int main(int argc, char *argv[])
{
  char wrd[MAX_STR_LEN];
  char nullText[1];
  FILE *txtFile;
  int a;
  int listSize; /* size of linked list */
  struct item root;
  struct item end;
  struct item *curItem;
  struct item *prevItem;
  struct item *newItem;

  /* set up end of linked list */
  end.count=0;
  nullText[0]='\0';
  end.text=nullText;
  end.nextItem=NULL;

  /* initialise linked list counter */
  listSize=0;

  /* Open text file for analysis */
  if (argc!=2)
    {
      printf("format: zipf textFileName\n");
      exit(1);
    }
  /* set pointer to input text file */
  argv++;
  if ((txtFile=fopen(*argv,"r"))==NULL)
    {
      printf("Error: can't open text file %s\n",*argv);
      exit(1);
    }
  /* Text file exists and is open */
  printf("Text file %s found\n",*argv);
  /* initialise word counter */
  a=0;
  /* Now go through the file and read the words */
  while (nextWord(txtFile,wrd)!=EOF)
    {
      if (strlen(wrd)>0)
	{
	  int i;

	  /* increment word counter */
	  a++;
	  
	  if (listSize==0)
	    {
	      /* create first proper item in linked list */
	      createNewItem(&root,newItem,&end,wrd,&listSize);
	    }
	  else
	    {
	      /* find word in linked list (if it is in the list already) */
	      /* initialise the search to start at beginning of list */
	      i=0;
	      curItem=root.nextItem;
	      prevItem=&root;
	      /* initialisation done */
	      while ((i<listSize)&&(strcmp(wrd,curItem->text)!=0))
		{
		  /* record current item in linked list */
		  prevItem=curItem;
		  /* and move on to next item */
		  curItem=curItem->nextItem;
		  /* increment linked list item counter */
		  i++;
		}
	      if (strcmp(wrd,curItem->text)==0)
		{
		  /* word is already in the list, just increment counter */
  		  curItem->count++;
		}
	      else
		{
		  /* create new item at this point in linked list */
		  createNewItem(prevItem,newItem,prevItem->nextItem,wrd,&listSize);
		}
	    }
	}
    }
  /* print something out to reassure user that the program hasn't crashed! */
  printf("\nText scanned: %d different words found\n",listSize);
  /* set root.count to be list size */
  root.count=listSize;
  /* sort the list according to word frequency */
  sortList(&root);
  /* print out the sorted linked list */
  checkList(root);
  /* now print to file */
  printf("Saving output in 'results'\n");
  printList(root);
  return(0);
}
