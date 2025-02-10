import { CreateItemControl } from "./CreateItemControl";
import { DeleteItemControl } from "./DeleteItemControl";
import { UpdateItemControl } from "./UpdateItemControl";

export const ControlPanel = ({
  handleSubmitPost,
  handleSubmitPut,
  handleSubmitDelete,
}: {
  handleSubmitPost: (form: React.FormEvent<HTMLFormElement>) => void;
  handleSubmitPut: (form: React.FormEvent<HTMLFormElement>) => void;
  handleSubmitDelete: (form: React.FormEvent<HTMLFormElement>) => void;
}) => {
  return (
    <div>
      <h2>Control Panel</h2>
      <div className="controlPanel">
        <CreateItemControl
          handleSubmitPost={handleSubmitPost}
        ></CreateItemControl>
        <UpdateItemControl
          handleSubmitPut={handleSubmitPut}
        ></UpdateItemControl>
        <DeleteItemControl
          handleSubmitDelete={handleSubmitDelete}
        ></DeleteItemControl>
      </div>
    </div>
  );
};
