export interface SparePart {
  spareTypeID: number;
  spareID: number;
  busID: number;

  spareName: string;
  spareTypeName: string;

  busName: string;
  quantity: number;
}

export interface SpareType {
  id: number;
  name: string;
}