import styled from 'styled-components';
import { LabelListTitle } from '../../../styles/typo';
import PostIndexLIstRender from '../../molecules/posts/PostIndexLIstRender';
import { BlueShadowButton } from '../../atoms/Buttons';
import DropdownSeriesList from '../../molecules/dropdown/DropdownSeriesList';
import usePostStore from '../../../store/postStore';

const Container = styled.div`
  box-sizing: border-box;
  display: flex;
  flex-direction: column;

  width: calc(100% / 12 * 10);
  height: fit-content;
  margin: 0 auto;
  justify-content: space-between;
  padding: 5px 31px;
  gap: 10px;
  margin-bottom: 5vh;
`;

const ContainerInner = styled.div`
  box-sizing: border-box;
  display: flex;
  flex-direction: row;

  width: 100%;
  height: fit-content;
  margin: 0 auto;
  justify-content: space-between;
  gap: 10px;
  margin-bottom: 5vh;
`;

const IndexLabelBox = styled.div`
  display: flex;
  width: 100%;
  flex-direction: column;
  gap: 10px;
  ${LabelListTitle};
`;

const PostSubHeaderLayer = ({ post }) => {
  const { isOpen, setIsOpen } = usePostStore();

  const toggleOpen = () => {
    setIsOpen();
  };
  return (
    <Container>
      <ContainerInner>
        <IndexLabelBox>
          Index
          <PostIndexLIstRender post={post} />
        </IndexLabelBox>
        <BlueShadowButton onClick={toggleOpen}> Add</BlueShadowButton>
      </ContainerInner>
      {isOpen ? <DropdownSeriesList post={post} /> : null}
    </Container>
  );
};

export default PostSubHeaderLayer;
