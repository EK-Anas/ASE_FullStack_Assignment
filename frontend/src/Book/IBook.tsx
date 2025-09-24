export interface Book {
  id: number;
  title: string;
  author: string;
  isbn: string;
  category: string;
  shelfLocation: string;
  copiesAvailable: number;
  fileUrl: string;
  fileSizeMb: number;
}
