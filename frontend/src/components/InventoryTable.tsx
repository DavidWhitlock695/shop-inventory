import { ItemObject } from "./ItemObject";
import { TableBody } from "./TableBody";
import { TableHeader } from "./TableHeader";

export const InventoryTable = ({ itemArray }: { itemArray: ItemObject[] }) => {
  return (
    <table>
      <TableHeader></TableHeader>
      <TableBody itemArray={itemArray}></TableBody>
    </table>
  );
};
