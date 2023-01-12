import styled from 'styled-components';
import { TextButton } from '../atoms/Buttons';

const SidebarTagsContainer = ({ isClicked, tags = ['발라드', '클래식', '댄스'] }) => {
  return (
    <Container isClicked={isClicked} tags={tags}>
      {tags.map(tag => {
        return <TextButton key={tag}>{tag}</TextButton>;
      })}
    </Container>
  );
};

const Container = styled.div`
  height: ${props => (props.isClicked ? `${props.tags.length * 30}px` : '0px')};
  overflow: hidden;
  transition: height 200ms ease-out;
`;

export default SidebarTagsContainer;
