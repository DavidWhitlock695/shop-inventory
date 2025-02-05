import { CreateItemControl } from "./CreateItemControl";
import { UpdateItemControl } from "./UpdateItemControl";

export const ControlPanel = ({
  handleSubmitPost,
  handleSubmitPut,
}: {
  handleSubmitPost: (form: React.FormEvent<HTMLFormElement>) => void;
  handleSubmitPut: (form: React.FormEvent<HTMLFormElement>) => void;
}) => {
  return (
    <div className="controlPanel">
      <h2>Control Panel</h2>
      <CreateItemControl
        handleSubmitPost={handleSubmitPost}
      ></CreateItemControl>
      <UpdateItemControl handleSubmitPut={handleSubmitPut}></UpdateItemControl>
    </div>
  );
};
